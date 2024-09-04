package com.emazon.stock.emazon.adapters.driving.http.mapper;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.stock.emazon.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-04T12:19:10-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ICategoryRequestMapperImpl implements ICategoryRequestMapper {

    @Override
    public Category addRequestToCategory(AddCategoryRequest addCategoryRequest) {
        if ( addCategoryRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = addCategoryRequest.getName();
        description = addCategoryRequest.getDescription();

        Long id = null;

        Category category = new Category( id, name, description );

        return category;
    }
}
