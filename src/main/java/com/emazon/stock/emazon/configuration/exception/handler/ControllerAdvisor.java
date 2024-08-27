package com.emazon.stock.emazon.configuration.exception.handler;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.emazon.stock.emazon.domain.exception.CategoryAlreadyExistsException;
import com.emazon.stock.emazon.domain.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.append(error.getDefaultMessage()).append(", ");
        }
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                errors.toString().trim().substring(0, errors.length() - 2),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
