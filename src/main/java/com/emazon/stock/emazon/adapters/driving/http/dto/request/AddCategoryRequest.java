package com.emazon.stock.emazon.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryRequest {
    @NotBlank(message = "Field name cannot be empty")
    @Size(message = "Name cannot be greater than 50 characters", max = 50)
    private String name;
    @NotBlank(message = "Field description cannot be empty")
    @Size(message = "Description cannot be greater than 90 characters", max = 90)
    private String description;

}
