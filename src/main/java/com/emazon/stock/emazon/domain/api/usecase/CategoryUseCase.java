package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.exception.CategoryAlreadyExistsException;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.exception.NoDataFoundException;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.emazon.domain.util.PaginationUtil;

import java.util.Optional;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        Optional<Category> existingCategory = categoryPersistencePort.findCategoryByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categoryPersistencePort.findCategoryByName(name);
    }

    @Override
    public Pagination<Category> getAllCategories(PaginationUtil paginationUtil) {
        Pagination<Category> categories = categoryPersistencePort.getAllCategories(paginationUtil);

        if (categories.getTotalElements() == 0) {
            throw new NoDataFoundException();
        }
        return categories;
    }
}
