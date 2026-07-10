package com.mirachi.exception;

@SuppressWarnings("serial")
public class RateMasterNotFoundException extends RuntimeException {

    public RateMasterNotFoundException(String message) {
        super(message);
    }
}