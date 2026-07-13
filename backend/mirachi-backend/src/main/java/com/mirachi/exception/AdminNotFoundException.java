package com.mirachi.exception;

@SuppressWarnings("serial")
public class AdminNotFoundException
        extends RuntimeException {

    public AdminNotFoundException(
            String message) {

        super(message);
    }
}