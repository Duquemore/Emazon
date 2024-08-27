package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.exception.CategoryAlreadyExistsException;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;

import java.util.List;
import java.util.Optional;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        Optional<Category> existingCategory = categoryPersistencePort.findByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryPersistencePort.findByName(name);
    }

    @Override
    public Long countCategories() {
        return categoryPersistencePort.countCategories();
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size, String sortDirection) {
        return this.categoryPersistencePort.getAllCategories(page, size, sortDirection);
    }
}
