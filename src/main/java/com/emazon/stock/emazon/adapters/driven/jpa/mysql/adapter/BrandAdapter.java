package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.spi.IBrandPersistencePort;
import com.emazon.stock.emazon.domain.util.Constants;
import com.emazon.stock.emazon.domain.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public Optional<Brand> findBrandByName(String name) {
        return brandRepository.findByName(name).map(brandEntityMapper::toModel);
    }

    @Override
    public Pagination<Brand> getAllBrands(PaginationUtil paginationUtil) {
        Sort.Direction direction = Sort.Direction.fromString(paginationUtil.getSortDirection().toUpperCase());
        PageRequest pageRequest = PageRequest.of(paginationUtil.getPage(), paginationUtil.getSize(), Sort.by(direction, Constants.SORT_BRANDS_BY_COLUMN));
        Page<BrandEntity> brandsEntities = brandRepository.findAll(pageRequest);
        List<Brand> brands = brandEntityMapper.toModelList(brandsEntities.getContent());
        return new Pagination<>(
                brands,
                brandsEntities.getTotalElements(),
                brandsEntities.getTotalPages()
        );
    }
}
