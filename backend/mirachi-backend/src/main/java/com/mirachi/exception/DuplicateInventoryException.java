package com.mirachi.exception;

@SuppressWarnings("serial")
public class DuplicateInventoryException
        extends RuntimeException {

    public DuplicateInventoryException(
            String message) {

        super(message);
    }
}