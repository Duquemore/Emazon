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
        if (name.length() > DomainConstants.MAX_NAME_LENGTH) {
            throw new LengthExceededInFieldException(DomainConstants.FIELD_NAME_MAX_LENGTH_MESSAGE);
        }
        if (name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_EMPTY_MESSAGE);
        }
        if (description.length() > DomainConstants.MAX_DESCRIPTION_LENGTH) {
            throw new LengthExceededInFieldException(DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE);
        }
        if (description.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE);
        }
        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
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
