package com.emazon.stock.emazon.adapters.driven.jpa.mysql.exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException(String message) {
        super(message);
    }
}
