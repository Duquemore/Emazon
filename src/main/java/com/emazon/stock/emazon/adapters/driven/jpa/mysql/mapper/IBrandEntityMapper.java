package com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stock.emazon.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    Brand toModel(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);
}
