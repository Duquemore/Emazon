package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @DisplayName("Dada una categoria debe guardarla en la base de datos ")
    void testSaveCategory() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Electronic things");

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(categoryPersistencePort).saveCategory(category);
    }

    @Test
    @DisplayName("Dado un nombre de categoria debe buscarlo en la base de datos")
    void testFindByName() {
        // Arrange
        String categoryName = "Electronics";
        Category expectedCategory = new Category(1L, "Electronics", "Electronic things");
        Optional<Category> expectedOptional = Optional.of(expectedCategory);

        when(categoryPersistencePort.findByName(categoryName)).thenReturn(expectedOptional);

        // Act
        Optional<Category> actualOptional = categoryUseCase.findByName(categoryName);

        // Assert
        assertEquals(expectedOptional, actualOptional);
    }

    @Test
    @DisplayName("Debe traer la cantidad de categorias existentes en la base de datos")
    void testCountCategories() {
        // Arrange
        long expectedCount = 5L;
        when(categoryPersistencePort.countCategories()).thenReturn(expectedCount);

        // Act
        Long actualCount = categoryUseCase.countCategories();

        // Assert
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Debe traer toda la lista de categorias de la base de datos paginadas y en orden ascendente")
    void testGetAllCategories() {
        // Arrange
        List<Category> expectedCategories = Collections.singletonList(new Category(1L, "Electronics", "Electronic things"));
        when(categoryPersistencePort.getAllCategories(anyInt(), anyInt(), anyString())).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = categoryUseCase.getAllCategories(0, 10, "ASC");

        // Assert
        assertEquals(expectedCategories, actualCategories);
    }
}