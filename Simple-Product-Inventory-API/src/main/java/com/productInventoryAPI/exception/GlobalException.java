package com.productInventoryAPI.exception;

import com.productInventoryAPI.entity.Product;
import com.productInventoryAPI.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(InvalidSkuFormatException.class)
    public ResponseEntity<ApiResponse<Product>> handleInvalidSkuFormateException(InvalidSkuFormatException e){
        log.error(e.getMessage());
        ApiResponse<Product> apiResponse = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.toString(),
                400,
                e.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(SkuAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Product>> handleSkuAlreadyExistsException(SkuAlreadyExistsException e){
        log.error(e.getMessage());
        ApiResponse<Product> apiResponse = new ApiResponse<>(
                HttpStatus.CONFLICT.toString(),
                409,
                e.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Product>> handleProductNotFoundException(ProductNotFoundException e){
        log.error(e.getMessage());
        ApiResponse<Product> apiResponse = new ApiResponse<>(
                HttpStatus.NOT_FOUND.toString(),
                404,
                e.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Product>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        for(FieldError fe: e.getBindingResult().getFieldErrors()){
            errors.put(fe.getField(),fe.getDefaultMessage());
            log.error("Validation failed: {}",fe.getDefaultMessage());
        }


        ApiResponse<Product> apiResponse = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.toString(),
                400,
                errors.values().toString(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
