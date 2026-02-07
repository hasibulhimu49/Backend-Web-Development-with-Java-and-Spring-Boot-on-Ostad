package com.authentication.Auth.exception.handle;

import com.authentication.Auth.dto.response.ApiResponse;
import com.authentication.Auth.exception.FileExpiredException;
import com.authentication.Auth.exception.TokenAlreadyExpiredException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.toString(),
                400,
                "Validation failed",
                true,
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenAlreadyExpiredException.class)
    public ResponseEntity<ApiResponse<Void>> handleTokenAlreadyExpiredException(TokenAlreadyExpiredException ex) {
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.GONE.toString(),
                410,
                ex.getMessage(),
                true,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }


    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityExistsException(EntityExistsException e){
        ApiResponse<Void> apiResponse =new  ApiResponse(
                HttpStatus.CONFLICT.toString(),
                409,
                e.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(EntityNotFoundException e){
        ApiResponse<Void> apiResponse =new  ApiResponse(
                HttpStatus.NOT_FOUND.toString(),
                404,
                e.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(FileExpiredException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileExpiredException(FileExpiredException e){
        ApiResponse<Void> apiResponse =new  ApiResponse(
                HttpStatus.GONE.toString(),
                410,
                e.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.GONE).body(apiResponse);
    }



    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<Void>> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.PAYLOAD_TOO_LARGE.toString(),
                413,
                "File too large! Maximum allowed size is 50MB.",
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(AccessDeniedException e){
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.FORBIDDEN.toString(),
                403,
                e.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }


}
