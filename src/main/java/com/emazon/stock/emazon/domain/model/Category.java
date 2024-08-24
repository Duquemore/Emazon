package com.emazon.stock.emazon.domain.model;

import com.emazon.stock.emazon.domain.exception.LengthExceededInFieldException;
import com.emazon.stock.emazon.domain.util.DomainConstants;
import com.emazon.stock.emazon.domain.exception.EmptyFieldException;

import static java.util.Objects.requireNonNull;

public class Category {
    private final Long id;
    private final String name;
    private final String description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
