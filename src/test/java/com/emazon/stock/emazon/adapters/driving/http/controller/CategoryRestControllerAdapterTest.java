package com.emazon.stock.emazon.adapters.driving.http.controller;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryRestControllerAdapterTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testAddCategory() {
        // Arrange
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("Category Name", "Category Description");
        Category category = new Category(1L, "Category Name", "Category Description");

        when(categoryRequestMapper.addRequestToCategory(addCategoryRequest)).thenReturn(category);

        // Act
        ResponseEntity<Category> response = categoryRestControllerAdapter.addCategory(addCategoryRequest);

        // Assert
        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
        verify(categoryRequestMapper, times(1)).addRequestToCategory(any(AddCategoryRequest.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
