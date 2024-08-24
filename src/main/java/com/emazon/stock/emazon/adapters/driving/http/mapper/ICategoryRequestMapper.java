package com.emazon.stock.emazon.adapters.driving.http.mapper;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.stock.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    @Mapping(target = "id", ignore = true)
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);
}
