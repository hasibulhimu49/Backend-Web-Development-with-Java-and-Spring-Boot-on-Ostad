package com.example.product_inventory_api.exception;

import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {InvalidSkuFormatException.class})
    public ResponseEntity<?> handleInvalidSkuFormat(InvalidSkuFormatException ex)
    {
        log.error("Validation failed: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error: ",ex.getMessage()));
    }


    @ExceptionHandler(SkuAlreadyExistException.class)
    public ResponseEntity<?> handleSkuAlreadyExist(SkuAlreadyExistException ex)
    {
        log.error("Validation failed: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message",ex.getMessage()));
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(ProductNotFoundException ex)
    {
        log.warn("Product not found with id: {}",ex.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        log.error("Validation failed: {}",e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
