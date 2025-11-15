package com.productInventoryAPI.exception;

public class SkuAlreadyExistsException extends RuntimeException{
    public SkuAlreadyExistsException(String message) {
        super(message);
    }
}
