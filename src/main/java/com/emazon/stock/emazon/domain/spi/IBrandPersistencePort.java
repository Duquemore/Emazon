package com.emazon.stock.emazon.domain.spi;

import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.util.PaginationUtil;

import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Optional<Brand> findBrandByName(String name);
    Pagination<Brand> getAllBrands(PaginationUtil paginationUtil);
}
