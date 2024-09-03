package com.emazon.stock.emazon.adapters.driving.http.mapper;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddBrandRequest;
import com.emazon.stock.emazon.domain.model.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-03T14:55:45-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class IBrandRequestMapperImpl implements IBrandRequestMapper {

    @Override
    public Brand addRequestToBrand(AddBrandRequest addBrandRequest) {
        if ( addBrandRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = addBrandRequest.getName();
        description = addBrandRequest.getDescription();

        Long id = null;

        Brand brand = new Brand( id, name, description );

        return brand;
    }
}