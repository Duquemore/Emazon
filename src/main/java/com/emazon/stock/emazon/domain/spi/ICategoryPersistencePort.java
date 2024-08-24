package com.emazon.stock.emazon.domain.spi;

import com.emazon.stock.emazon.domain.model.Category;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);

    Optional<Category> findByName(String name);
}
