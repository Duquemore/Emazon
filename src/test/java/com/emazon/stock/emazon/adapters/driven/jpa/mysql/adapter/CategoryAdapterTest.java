package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @DisplayName("Dada una categoria debe guardarla en la base datos")
    void testSaveCategory() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Name", "Category Description");

        // Mocking the conversion from domain to entity
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    @DisplayName("Dado un nombre de una categoria debe buscarlo en la base de datos")
    void testFindByName() {
        // Arrange
        String categoryName = "Category Name";
        Category category = new Category(1L, categoryName, "Category Description");
        com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Name", "Category Description");

        // Mocking the repository and mapper methods
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toModel(categoryEntity)).thenReturn(category);

        // Act
        Optional<Category> result = categoryAdapter.findByName(categoryName);

        // Assert
        assertEquals(Optional.of(category), result);
        verify(categoryRepository, times(1)).findByName(categoryName);
    }

    @Test
    @DisplayName("Debe traer la cantidad de categorias almacenadas en la base de datos")
    void testCountCategories() {
        // Arrange
        long expectedCount = 5L;
        when(categoryRepository.count()).thenReturn(expectedCount);

        // Act
        Long actualCount = categoryAdapter.countCategories();

        // Assert
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Debe traer la lista de Categorias de la base de datos paginada y en orden ascendente")
    void testGetAllCategories() {
        // Arrange
        List<CategoryEntity> categoryEntities = Collections.singletonList(new CategoryEntity());
        List<Category> categories = Collections.singletonList(new Category(1L, "Category Name", "Category Description"));
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, Constants.SORT_CATEGORIES_BY_COLUMN));

        when(categoryRepository.findAll(pageable)).thenReturn(new PageImpl<>(categoryEntities));
        when(categoryEntityMapper.toModelList(categoryEntities)).thenReturn(categories);

        // Act
        List<Category> result = categoryAdapter.getAllCategories(0, 10, "ASC");

        // Assert
        assertEquals(categories, result);
    }
}