package com.url.exception.handler;

public class UrlExpiredException extends RuntimeException{
    public UrlExpiredException(String message){
        super(message);
    }
}
