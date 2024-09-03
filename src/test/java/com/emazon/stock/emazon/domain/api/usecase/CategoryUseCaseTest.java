package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.exception.NoDataFoundException;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.emazon.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
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

        when(categoryPersistencePort.findCategoryByName(categoryName)).thenReturn(expectedOptional);

        // Act
        Optional<Category> actualOptional = categoryUseCase.findCategoryByName(categoryName);

        // Assert
        assertEquals(expectedOptional, actualOptional);
    }

    @Test
    @DisplayName("Debe traer toda la lista de categorias de la base de datos paginadas y en orden ascendente")
    void testGetAllCategories() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(0, 10, "asc");
        Pagination<Category> pagination = new Pagination<>(Collections.singletonList(new Category(1L, "Electronics", "Electronic things")), 1, 1);

        when(categoryPersistencePort.getAllCategories(paginationUtil)).thenReturn(pagination);

        // Act
        Pagination<Category> result = categoryUseCase.getAllCategories(paginationUtil);

        // Assert
        assertEquals(1, result.getTotalElements());
        verify(categoryPersistencePort, times(1)).getAllCategories(paginationUtil);
    }

    @Test
    @DisplayName("Debe lanzar un NoDataFoundException cuando las categorias estan vacias")
    void getAllCategoriesShouldThrowNoDataFoundExceptionWhenEmpty() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(0, 10, "asc");
        Pagination<Category> pagination = new Pagination<>(Collections.emptyList(), 0, 0);

        when(categoryPersistencePort.getAllCategories(paginationUtil)).thenReturn(pagination);

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> {
            categoryUseCase.getAllCategories(paginationUtil);
        });

        verify(categoryPersistencePort, times(1)).getAllCategories(paginationUtil);
    }
}