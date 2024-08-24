package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_whenCategoryDoesNotExist_shouldSaveCategory() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");

        when(categoryPersistencePort.findByName(category.getName())).thenReturn(Optional.empty());

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void saveCategory_whenCategoryAlreadyExists_shouldThrowException() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");

        when(categoryPersistencePort.findByName(category.getName())).thenReturn(Optional.of(category));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));

        // Verificar que no se intenta guardar la categoría si ya existe
        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }
}
