package com.emazon.stock.emazon.adapters.driving.http.controller;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Category API", description = "API para manejar las categorias")
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort iCategoryServicePort;
    private final ICategoryRequestMapper iCategoryRequestMapper;
    final ICategoryResponseMapper iCategoryResponseMapper;

    public CategoryRestControllerAdapter(ICategoryServicePort iCategoryServicePort, ICategoryRequestMapper iCategoryRequestMapper, ICategoryResponseMapper iCategoryResponseMapper) {
        this.iCategoryServicePort = iCategoryServicePort;
        this.iCategoryRequestMapper = iCategoryRequestMapper;
        this.iCategoryResponseMapper = iCategoryResponseMapper;
    }

    @PostMapping
    @Operation(summary = "Guardar una categoria", description = "Guarda una nueva categoria")
    public ResponseEntity<Category> addCategory(@RequestBody AddCategoryRequest category) {
        iCategoryServicePort.saveCategory(iCategoryRequestMapper.addRequestToCategory(category));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
