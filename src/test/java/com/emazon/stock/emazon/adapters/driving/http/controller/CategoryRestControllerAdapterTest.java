package com.emazon.stock.emazon.adapters.driving.http.controller;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.stock.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerAdapterTest {

    private MockMvc mockMvc;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestControllerAdapter).build();
    }

    @Test
    @DisplayName("Dada una categoria la agrega a la base de datos")
    void testAddCategory() {
        // Arrange
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("Category Name", "Category Description");
        Category category = new Category(1L, "Category Name", "Category Description");

        when(categoryRequestMapper.addRequestToCategory(addCategoryRequest)).thenReturn(category);

        // Act
        ResponseEntity<Void> response = categoryRestControllerAdapter.addCategory(addCategoryRequest);

        // Assert
        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
        verify(categoryRequestMapper, times(1)).addRequestToCategory(any(AddCategoryRequest.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Debe traer la lista de categorias almacenadas en la base de datos paginada y en orden ascendente, ademas de otros atributos como la cantidad de categorias almacenadas y el total de paginas")
    void testGetAllCategories() throws Exception {
        // Arrange
        List<Category> categoryList = Collections.singletonList(new Category(1L, "Category Name", "Category Description"));
        List<CategoryResponse> categoryResponseList = Collections.singletonList(new CategoryResponse(1L, "Category Name", "Category Description"));
        long totalCategories = 10L;
        int totalPages = 1;

        when(categoryServicePort.getAllCategories(anyInt(), anyInt(), anyString())).thenReturn(categoryList);
        when(categoryServicePort.countCategories()).thenReturn(totalCategories);
        when(categoryResponseMapper.toCategoryResponseList(categoryList)).thenReturn(categoryResponseList);

        // Act & Assert
        mockMvc.perform(get("/api/categorias")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sortDirection", "ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCategories").value(totalCategories))
                .andExpect(jsonPath("$.totalPages").value(totalPages))
                .andExpect(jsonPath("$.categories").isArray());
    }
}
