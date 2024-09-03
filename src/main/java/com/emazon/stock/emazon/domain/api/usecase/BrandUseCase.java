package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.api.IBrandServicePort;
import com.emazon.stock.emazon.domain.exception.BrandAlreadyExistsException;
import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.spi.IBrandPersistencePort;

import java.util.Optional;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        Optional<Brand> existingBrand = brandPersistencePort.findBrandByName(brand.getName());
        if (existingBrand.isPresent()) {
            throw new BrandAlreadyExistsException();
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Optional<Brand> findBrandByName(String name) {
        return brandPersistencePort.findBrandByName(name);
    }
}
