package com.emazon.stock.emazon.adapters.driving.http.mapper;

import com.emazon.stock.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stock.emazon.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-24T16:34:47-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ICategoryResponseMapperImpl implements ICategoryResponseMapper {

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryResponse categoryResponse = new CategoryResponse( id, name, description );

        return categoryResponse;
    }
}
