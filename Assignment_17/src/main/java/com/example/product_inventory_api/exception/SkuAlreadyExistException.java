package com.example.product_inventory_api.exception;

public class SkuAlreadyExistException extends RuntimeException {
    public SkuAlreadyExistException(String message) {
        super(message);
    }
}
