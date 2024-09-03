package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stock.emazon.domain.exception.NoDataFoundException;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.util.Constants;
import com.emazon.stock.emazon.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Name", "Category Description");

        // Mocking the repository and mapper methods
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toModel(categoryEntity)).thenReturn(category);

        // Act
        Optional<Category> result = categoryAdapter.findCategoryByName(categoryName);

        // Assert
        assertEquals(Optional.of(category), result);
        verify(categoryRepository, times(1)).findByName(categoryName);
    }

    @Test
    @DisplayName("Debe traer la lista de Categorias de la base de datos paginada y en orden ascendente")
    void testGetAllCategories() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortDirection = "asc";
        PaginationUtil paginationUtil = new PaginationUtil(page, size, sortDirection);

        CategoryEntity categoryEntity = new CategoryEntity();
        Category category = new Category(1L, "Category Name", "Category Description");
        List<CategoryEntity> categoryEntities = Collections.singletonList(categoryEntity);
        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntities, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, Constants.SORT_CATEGORIES_BY_COLUMN)), 1);

        when(categoryRepository.findAll(any(PageRequest.class))).thenReturn(categoryEntityPage);
        when(categoryEntityMapper.toModelList(categoryEntities)).thenReturn(Collections.singletonList(category));

        // Act
        Pagination<Category> result = categoryAdapter.getAllCategories(paginationUtil);

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getElements().size());
        verify(categoryRepository, times(1)).findAll(any(PageRequest.class));
    }
}