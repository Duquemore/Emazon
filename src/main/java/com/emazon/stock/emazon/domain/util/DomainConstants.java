package com.emazon.stock.emazon.domain.util;

public class DomainConstants {

    private DomainConstants(){
        throw new IllegalStateException("utility class");
    }

    public enum Field {
        NAME,
        PRICE,
        QUANTITY,
        CONTACTNUMBER
    }

    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_DESCRIPTION_LENGTH = 90;
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_EMPTY_MESSAGE = "Field 'name' cannot be empty";
    public static final String FIELD_DESCRIPTION_EMPTY_MESSAGE = "Field 'description' cannot be empty";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "Field name cannot be greater than 50 characters";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "Field description cannot be greater than 90 characters";
}
