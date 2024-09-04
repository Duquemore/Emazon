package com.emazon.stock.emazon.domain.util;

public class Constants {
    private Constants() {
        throw new IllegalStateException("utility class");
    }

    public static final String NAME_EMPTY_EXCEPTION_MESSAGE = "El campo nombre no puede estar vacio";
    public static final String NAME_GREATER_THAN_50_CHARACTERS_EXCEPTION_MESSAGE = "El campo nombre no puede ser mayor a 50 caracteres";
    public static final String DESCRIPTION_EMPTY_EXCEPTION_MESSAGE = "El campo descripcion no puede estar vacio";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No se encontraron datos en la base de datos";

    //Category
    public static final int CATEGORY_NAME_MAX_LENGTH = 50;
    public static final int CATEGORY_DESCRIPTION_MAX_LENGTH = 90;
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "La categoria que quieres crear ya existe";
    public static final String SORT_CATEGORIES_BY_COLUMN = "name";
    public static final String DESCRIPTION_GREATER_THAN_90_CHARACTERS_EXCEPTION_MESSAGE = "El campo descripcion no puede ser mayor a 90 caracteres";

    //Brand
    public static final int BRAND_NAME_MAX_LENGTH = 50;
    public static final int BRAND_DESCRIPTION_MAX_LENGTH = 120;
    public static final String BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE = "La marca que quieres crear ya existe";
    public static final String SORT_BRANDS_BY_COLUMN = "name";
    public static final String DESCRIPTION_GREATER_THAN_120_CHARACTERS_EXCEPTION_MESSAGE = "El campo descripcion no puede ser mayor a 120 caracteres";
}
