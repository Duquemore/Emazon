package com.emazon.stock.emazon.domain.api;

import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.util.PaginationUtil;

import java.util.List;
import java.util.Optional;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    Optional<Category> findCategoryByName(String name);
    Pagination<Category> getAllCategories(PaginationUtil paginationUtil);
}

