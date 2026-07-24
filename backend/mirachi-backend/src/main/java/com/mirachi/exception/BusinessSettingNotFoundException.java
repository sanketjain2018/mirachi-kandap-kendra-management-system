package com.mirachi.exception;

@SuppressWarnings("serial")
public class BusinessSettingNotFoundException extends RuntimeException {
    public BusinessSettingNotFoundException(String message) {

	super(message);
    }
}
