package com.emazon.stock.emazon.domain.util;

public class Constants {
    private Constants() {
        throw new IllegalStateException("utility class");
    }

    public static final int CATEGORY_NAME_MAX_LENGTH = 50;
    public static final int CATEGORY_DESCRIPTION_MAX_LENGTH = 90;
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Category you want to create already exists";
    public static final String NAME_EMPTY_EXCEPTION_MESSAGE = "Field name cannot be empty";
    public static final String NAME_GREATER_THAN_50_CHARACTERS_EXCEPTION_MESSAGE = "Field name cannot be greater than 50 characters";
    public static final String DESCRIPTION_EMPTY_EXCEPTION_MESSAGE = "Field description cannot be empty";
    public static final String DESCRIPTION_GREATER_THAN_90_CHARACTERS_EXCEPTION_MESSAGE = "Field description cannot be greater than 90 characters";

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String SORT_CATEGORIES_BY_COLUMN = "name";

}
