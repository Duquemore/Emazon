package com.emazon.stock.emazon.domain.spi;

import com.emazon.stock.emazon.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    Optional<Category> findByName(String name);
    Long countCategories();
    List<Category> getAllCategories(Integer page, Integer size, String sortDirection);
}
