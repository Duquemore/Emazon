package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryAdapterTest {

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "Category Name";
    private static final String CATEGORY_DESCRIPTION = "Category Description";

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
    void testSaveCategoryWhenCategoryDoesNotExist() {
        // Arrange
        Category category = new Category(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);

        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(any());

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void testSaveCategoryWhenCategoryAlreadyExists() {
        // Arrange
        Category category = new Category(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);
        CategoryEntity categoryEntity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);

        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(categoryEntity));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryAdapter.saveCategory(category));

        // Verificar que no se intente guardar la categoría si ya existe
        verify(categoryRepository, never()).save(any());
    }
}
