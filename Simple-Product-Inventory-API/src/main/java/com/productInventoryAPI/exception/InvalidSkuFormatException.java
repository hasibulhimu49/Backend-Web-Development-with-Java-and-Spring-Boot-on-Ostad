package com.productInventoryAPI.exception;

public class InvalidSkuFormatException extends RuntimeException{
    public InvalidSkuFormatException(String message) {
        super(message);
    }
}
