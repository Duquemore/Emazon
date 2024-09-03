package com.emazon.stock.emazon.adapters.driving.http.mapper;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddBrandRequest;
import com.emazon.stock.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {
    @Mapping(target = "id", ignore = true)
    Brand addRequestToBrand(AddBrandRequest addBrandRequest);
}
