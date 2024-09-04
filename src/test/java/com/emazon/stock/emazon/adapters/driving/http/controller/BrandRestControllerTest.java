package com.emazon.stock.emazon.adapters.driving.http.controller;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddBrandRequest;
import com.emazon.stock.emazon.adapters.driving.http.dto.response.BrandResponse;
import com.emazon.stock.emazon.adapters.driving.http.mapper.IBrandRequestMapper;
import com.emazon.stock.emazon.adapters.driving.http.mapper.IBrandResponseMapper;
import com.emazon.stock.emazon.domain.api.IBrandServicePort;
import com.emazon.stock.emazon.domain.model.Brand;
import com.emazon.stock.emazon.domain.model.Pagination;
import com.emazon.stock.emazon.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BrandRestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandRestController brandRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(brandRestController).build();
    }

    @Test
    @DisplayName("Dada una marca la agrega a la base de datos")
    void testAddBrand() {
        // Arrange
        AddBrandRequest addBrandRequest = new AddBrandRequest("Brand Name", "Brand Description");
        Brand brand = new Brand(1L, "Brand Name", "Brand Description");

        when(brandRequestMapper.addRequestToBrand(addBrandRequest)).thenReturn(brand);

        // Act
        ResponseEntity<Void> response = brandRestController.addBrand(addBrandRequest);

        // Assert
        verify(brandServicePort, times(1)).saveBrand(any(Brand.class));
        verify(brandRequestMapper, times(1)).addRequestToBrand(any(AddBrandRequest.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Debe traer la lista de marcas almacenadas en la base de datos paginada y en orden ascendente, ademas de otros atributos como la cantidad de categorias almacenadas y el total de paginas")
    void testGetAllBrands() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortDirection = "asc";

        Brand brand = new Brand(1L, "Electronics", "Brand Description");

        List<Brand> brandList = Collections.singletonList(brand);
        Pagination<Brand> pagination = new Pagination<>(brandList, 1, 1);

        when(brandServicePort.getAllBrands(any(PaginationUtil.class))).thenReturn(pagination);

        BrandResponse brandResponse = new BrandResponse(1L, "Electronics", "Brand Description");

        List<BrandResponse> brandResponseList = Collections.singletonList(brandResponse);

        when(brandResponseMapper.toBrandResponseList(brandList)).thenReturn(brandResponseList);

        // Act
        ResponseEntity<Pagination<BrandResponse>> response = brandRestController.getAllBrands(page, size, sortDirection);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals(1, response.getBody().getTotalPages());
        assertEquals(1, response.getBody().getElements().size());
        assertEquals("Electronics", response.getBody().getElements().get(0).getName());

        verify(brandServicePort, times(1)).getAllBrands(any(PaginationUtil.class));
        verify(brandResponseMapper, times(1)).toBrandResponseList(brandList);
    }
}