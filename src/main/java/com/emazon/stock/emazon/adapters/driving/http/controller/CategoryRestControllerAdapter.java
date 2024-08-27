package com.emazon.stock.emazon.adapters.driving.http.controller;

import com.emazon.stock.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.stock.emazon.adapters.driving.http.dto.response.CategoriesResponse;
import com.emazon.stock.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.emazon.stock.emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "API para manejar las categorias")
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping
    @Operation(summary = "Guardar categoria", description = "Guarda una nueva categoria en la base de datos")
    public ResponseEntity<Void> addCategory(@Valid @RequestBody AddCategoryRequest category) {
        categoryServicePort.saveCategory(categoryRequestMapper.addRequestToCategory(category));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Listar las categorias de la base de datos")
    public ResponseEntity<CategoriesResponse> getAllCategories(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortDirection) {

        List<CategoryResponse> categories = categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories(page - 1, size, sortDirection));
        Long totalCategories = categoryServicePort.countCategories();
        int totalPages = (int) Math.ceil((double) totalCategories / size);

        CategoriesResponse categoriesResponse = new CategoriesResponse(categories, totalCategories, totalPages);

        return ResponseEntity.ok(categoriesResponse);
    }


}
