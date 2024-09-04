package com.emazon.stock.emazon.adapters.driving.http.controller;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.stock.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.util.PaginationUtil;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
    }

    @Test
    @DisplayName("Dada una categoria la agrega a la base de datos")
    void testAddCategory() {
        // Arrange
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("Category Name", "Category Description");
        Category category = new Category(1L, "Category Name", "Category Description");

        when(categoryRequestMapper.addRequestToCategory(addCategoryRequest)).thenReturn(category);

        // Act
        ResponseEntity<Void> response = categoryRestController.addCategory(addCategoryRequest);

        // Assert
        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
        verify(categoryRequestMapper, times(1)).addRequestToCategory(any(AddCategoryRequest.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Debe traer la lista de categorias almacenadas en la base de datos paginada y en orden ascendente, ademas de otros atributos como la cantidad de categorias almacenadas y el total de paginas")
    void testGetAllCategories() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortDirection = "asc";

        Category category = new Category(1L, "Electronics", "Category Description");

        List<Category> categoryList = Collections.singletonList(category);
        Pagination<Category> pagination = new Pagination<>(categoryList, 1, 1);

        when(categoryServicePort.getAllCategories(any(PaginationUtil.class))).thenReturn(pagination);

        CategoryResponse categoryResponse = new CategoryResponse(1L, "Electronics", "Category Description");

        List<CategoryResponse> categoryResponseList = Collections.singletonList(categoryResponse);

        when(categoryResponseMapper.toCategoryResponseList(categoryList)).thenReturn(categoryResponseList);

        // Act
        ResponseEntity<Pagination<CategoryResponse>> response = categoryRestController.getAllCategories(page, size, sortDirection);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals(1, response.getBody().getTotalPages());
        assertEquals(1, response.getBody().getElements().size());
        assertEquals("Electronics", response.getBody().getElements().get(0).getName());

        verify(categoryServicePort, times(1)).getAllCategories(any(PaginationUtil.class));
        verify(categoryResponseMapper, times(1)).toCategoryResponseList(categoryList);
    }

}
