package com.emazon.stock.emazon.adapters.driving.http.controller;
import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddBrandRequest;
import com.emazon.stock.emazon.adapters.driving.http.mapper.IBrandRequestMapper;
import com.emazon.stock.emazon.domain.api.IBrandServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
@Tag(name = "Brand API", description = "API para manejar las marcas")
public class BrandRestController {

    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;

    @PostMapping
    @Operation(summary = "Guardar marca", description = "Guarda una nueva marca en la base de datos")
    public ResponseEntity<Void> addBrand(@Valid @RequestBody AddBrandRequest brand) {
        brandServicePort.saveBrand(brandRequestMapper.addRequestToBrand(brand));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
