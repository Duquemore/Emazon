package com.emazon.stock.emazon.domain.api.usecase;

import com.emazon.stock.emazon.domain.exception.NoDataFoundException;
import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.spi.IBrandPersistencePort;
import com.emazon.stock.emazon.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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

    @Test
    @DisplayName("Debe traer toda la lista de marcas de la base de datos paginadas y en orden ascendente")
    void testGetAllBrands() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(0, 10, "asc");
        Pagination<Brand> pagination = new Pagination<>(Collections.singletonList(new Brand(1L, "Electronics", "Electronic things")), 1, 1);

        when(brandPersistencePort.getAllBrands(paginationUtil)).thenReturn(pagination);

        // Act
        Pagination<Brand> result = brandUseCase.getAllBrands(paginationUtil);

        // Assert
        assertEquals(1, result.getTotalElements());
        verify(brandPersistencePort, times(1)).getAllBrands(paginationUtil);
    }

    @Test
    @DisplayName("Debe lanzar un NoDataFoundException cuando la base de datos de marcas esta vacia")
    void getAllBrandsShouldThrowNoDataFoundExceptionWhenEmpty() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(0, 10, "asc");
        Pagination<Brand> pagination = new Pagination<>(Collections.emptyList(), 0, 0);

        when(brandPersistencePort.getAllBrands(paginationUtil)).thenReturn(pagination);

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> {
            brandUseCase.getAllBrands(paginationUtil);
        });

        verify(brandPersistencePort, times(1)).getAllBrands(paginationUtil);
    }
}