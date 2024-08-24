package com.emazon.stock.emazon.configuration.exception.handler;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.emazon.stock.emazon.configuration.Constants;
import com.emazon.stock.emazon.domain.exception.EmptyFieldException;
import com.emazon.stock.emazon.domain.exception.LengthExceededInFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice

public class ControllerAdvisor {
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(LengthExceededInFieldException.class)
    public ResponseEntity<ExceptionResponse> handleMaximimLengthExceededInFieldException(LengthExceededInFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
