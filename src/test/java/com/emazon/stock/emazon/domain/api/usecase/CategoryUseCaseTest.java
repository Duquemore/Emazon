package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort iCategoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategoryShouldInvokeSaveCategoryOnPersistencePort() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Electronic things");

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(iCategoryPersistencePort).saveCategory(category);
    }
}