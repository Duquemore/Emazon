package com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stock.emazon.domain.model.Brand;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-04T12:19:11-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class IBrandEntityMapperImpl implements IBrandEntityMapper {

    @Override
    public Brand toModel(BrandEntity brandEntity) {
        if ( brandEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = brandEntity.getId();
        name = brandEntity.getName();
        description = brandEntity.getDescription();

        Brand brand = new Brand( id, name, description );

        return brand;
    }

    @Override
    public BrandEntity toEntity(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId( brand.getId() );
        brandEntity.setName( brand.getName() );
        brandEntity.setDescription( brand.getDescription() );

        return brandEntity;
    }

    @Override
    public List<Brand> toModelList(List<BrandEntity> BrandEntities) {
        if ( BrandEntities == null ) {
            return null;
        }

        List<Brand> list = new ArrayList<Brand>( BrandEntities.size() );
        for ( BrandEntity brandEntity : BrandEntities ) {
            list.add( toModel( brandEntity ) );
        }

        return list;
    }
}
