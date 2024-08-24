package com.emazon.stock.emazon.adapters.driving.http.dto.request;

public class AddCategoryRequest {
    private final String name;
    private final String description;


    public AddCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
