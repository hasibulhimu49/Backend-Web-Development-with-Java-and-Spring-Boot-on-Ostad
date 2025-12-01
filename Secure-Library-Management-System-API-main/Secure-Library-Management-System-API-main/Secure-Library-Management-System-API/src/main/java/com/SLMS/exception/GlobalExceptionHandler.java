package com.SLMS.exception;

import com.SLMS.dto.response.ApiResponse;
import com.SLMS.dto.response.BookResponse;
import com.SLMS.dto.response.RegisterUserResponse;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ApiResponse<RegisterUserResponse>> handleEntityExistsException(EntityExistsException ex) {
        log.error(ex.getMessage());

        ApiResponse<RegisterUserResponse> response = new ApiResponse<>(
                HttpStatus.CONFLICT.toString(),
                409,
                ex.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<RegisterUserResponse>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error(ex.getMessage());
        ApiResponse<RegisterUserResponse> response = new ApiResponse<>(
                HttpStatus.NOT_FOUND.toString(),
                404,
                ex.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse<BookResponse>> handleBookNotFoundException(BookNotFoundException ex) {
        log.error(ex.getMessage());
        ApiResponse<BookResponse> response = new ApiResponse<>(
                HttpStatus.NOT_FOUND.toString(),
                404,
                ex.getMessage(),
                true,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
