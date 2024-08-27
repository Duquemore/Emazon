package com.emazon.stock.emazon.adapters.driving.http.dto.request;

import com.emazon.stock.emazon.domain.util.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddCategoryRequest {
    @NotBlank(message = Constants.NAME_EMPTY_EXCEPTION_MESSAGE)
    @Size(message = Constants.NAME_GREATER_THAN_50_CHARACTERS_EXCEPTION_MESSAGE, max = Constants.CATEGORY_NAME_MAX_LENGTH)
    private final String name;
    @NotBlank(message = Constants.DESCRIPTION_EMPTY_EXCEPTION_MESSAGE)
    @Size(message = Constants.DESCRIPTION_GREATER_THAN_90_CHARACTERS_EXCEPTION_MESSAGE, max = Constants.CATEGORY_DESCRIPTION_MAX_LENGTH)
    private final String description;
}
