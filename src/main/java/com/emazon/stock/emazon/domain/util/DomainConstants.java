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

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";

}
