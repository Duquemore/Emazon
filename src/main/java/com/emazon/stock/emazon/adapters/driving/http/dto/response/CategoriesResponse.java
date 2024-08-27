package com.emazon.stock.emazon.adapters.driving.http.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CategoriesResponse {
    private final List<CategoryResponse> categories;
    private final Long totalCategories;
    private final int totalPages;
}
