package com.emazon.stock.emazon.domain.api;

import com.emazon.stock.emazon.domain.model.Category;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}
