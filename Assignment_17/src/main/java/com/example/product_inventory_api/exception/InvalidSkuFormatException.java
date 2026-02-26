package com.example.product_inventory_api.exception;

public class InvalidSkuFormatException extends RuntimeException {
    public InvalidSkuFormatException(String message) {
        super(message);
    }
}
