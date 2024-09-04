package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;


    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Dada una marca debe guardarla en la base de datos ")
    void testSaveBrand() {
        // Arrange
        Brand brand = new Brand(1L, "Electronics", "Electronic things");

        // Act
        brandUseCase.saveBrand(brand);

        // Assert
        verify(brandPersistencePort).saveBrand(brand);
    }

    @Test
    @DisplayName("Dado un nombre de marca debe buscarlo en la base de datos")
    void testFindByName() {
        // Arrange
        String brandName = "Electronics";
        Brand expectedBrand = new Brand(1L, "Electronics", "Electronic things");
        Optional<Brand> expectedOptional = Optional.of(expectedBrand);

        when(brandPersistencePort.findBrandByName(brandName)).thenReturn(expectedOptional);

        // Act
        Optional<Brand> actualOptional = brandUseCase.findBrandByName(brandName);

        // Assert
        assertEquals(expectedOptional, actualOptional);
    }
}