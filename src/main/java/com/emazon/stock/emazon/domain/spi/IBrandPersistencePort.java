package com.emazon.stock.emazon.domain.spi;

import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Optional<Brand> findBrandByName(String name);
}
