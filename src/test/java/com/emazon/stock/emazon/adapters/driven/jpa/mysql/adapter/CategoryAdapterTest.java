package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");
        com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Name", "Category Description");

        // Mocking the conversion from domain to entity
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void testFindByName() {
        // Arrange
        String categoryName = "Category Name";
        Category category = new Category(1L, categoryName, "Category Description");
        com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Name", "Category Description");

        // Mocking the repository and mapper methods
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toDomain(categoryEntity)).thenReturn(category);

        // Act
        Optional<Category> result = categoryAdapter.findByName(categoryName);

        // Assert
        assertEquals(Optional.of(category), result);
        verify(categoryRepository, times(1)).findByName(categoryName);
    }
}
