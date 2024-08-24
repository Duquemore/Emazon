package com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.emazon.stock.emazon.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-24T16:36:59-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ICategoryEntityMapperImpl implements ICategoryEntityMapper {

    @Override
    public Category toDomain(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        Category category = new Category( id, name, description );

        return category;
    }

    @Override
    public Category toModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        Category category = new Category( id, name, description );

        return category;
    }

    @Override
    public CategoryEntity toEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryEntity categoryEntity = new CategoryEntity( id, name, description );

        return categoryEntity;
    }
}
