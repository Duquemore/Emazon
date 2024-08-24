package com.emazon.stock.emazon.domain.spi;

import com.emazon.stock.emazon.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
}
