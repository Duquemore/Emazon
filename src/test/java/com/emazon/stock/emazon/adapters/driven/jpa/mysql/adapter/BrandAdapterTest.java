package com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.emazon.stock.emazon.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BrandAdapterTest {
    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Dada una marca debe guardarla en la base datos")
    void testSaveBrand() {
        // Arrange
        Brand brand = new Brand(1L, "Brand Name", "Brand Description");
        BrandEntity brandEntity = new BrandEntity(1L, "Brand Name", "Brand Description");

        // Mocking the conversion from domain to entity
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);

        // Act
        brandAdapter.saveBrand(brand);

        // Assert
        verify(brandRepository, times(1)).save(brandEntity);
    }

    @Test
    @DisplayName("Dado un nombre de una marca debe buscarlo en la base de datos")
    void testFindByName() {
        // Arrange
        String brandName = "Brand Name";
        Brand brand = new Brand(1L, brandName, "Brand Description");
        BrandEntity brandEntity = new BrandEntity(1L, "Brand Name", "Brand Description");

        // Mocking the repository and mapper methods
        when(brandRepository.findByName(brandName)).thenReturn(Optional.of(brandEntity));
        when(brandEntityMapper.toModel(brandEntity)).thenReturn(brand);

        // Act
        Optional<Brand> result = brandAdapter.findBrandByName(brandName);

        // Assert
        assertEquals(Optional.of(brand), result);
        verify(brandRepository, times(1)).findByName(brandName);
    }
}