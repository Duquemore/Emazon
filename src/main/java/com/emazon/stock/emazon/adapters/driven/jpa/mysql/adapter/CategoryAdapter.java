package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.model.Category;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.emazon.domain.util.Constants;
import com.emazon.stock.emazon.domain.util.PaginationUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name).map(categoryEntityMapper::toModel);
    }

    @Override
    public Pagination<Category> getAllCategories(PaginationUtil paginationUtil) {
        Sort.Direction direction = Sort.Direction.fromString(paginationUtil.getSortDirection().toUpperCase());
        PageRequest pageRequest = PageRequest.of(paginationUtil.getPage(), paginationUtil.getSize(), Sort.by(direction, Constants.SORT_CATEGORIES_BY_COLUMN));
        Page<CategoryEntity> categoriesEntities = categoryRepository.findAll(pageRequest);
        List<Category> categories = categoryEntityMapper.toModelList(categoriesEntities.getContent());
        return new Pagination<>(
                categories,
                categoriesEntities.getTotalElements(),
                categoriesEntities.getTotalPages()
        );
    }
}
