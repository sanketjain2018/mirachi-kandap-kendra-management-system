package com.mirachi.exception;

@SuppressWarnings("serial")
public class BillNotFoundException
        extends RuntimeException {

    public BillNotFoundException(
            String message) {

        super(message);
    }
}