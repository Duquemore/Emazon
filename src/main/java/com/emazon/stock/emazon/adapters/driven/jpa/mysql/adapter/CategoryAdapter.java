package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;

public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public void saveCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }
}
