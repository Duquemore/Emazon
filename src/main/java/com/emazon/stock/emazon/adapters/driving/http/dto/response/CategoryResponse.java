package com.emazon.stock.emazon.adapters.driving.http.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryResponse {
    private final Long id;
    private final String name;
    private final String description;
}
