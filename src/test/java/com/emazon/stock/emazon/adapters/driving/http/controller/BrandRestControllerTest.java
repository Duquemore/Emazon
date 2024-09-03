package com.emazon.stock.emazon.adapters.driving.http.controller;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddBrandRequest;
import com.emazon.stock.emazon.adapters.driving.http.mapper.IBrandRequestMapper;
import com.emazon.stock.emazon.domain.api.IBrandServicePort;
import com.emazon.stock.emazon.domain.model.Brand;
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
}