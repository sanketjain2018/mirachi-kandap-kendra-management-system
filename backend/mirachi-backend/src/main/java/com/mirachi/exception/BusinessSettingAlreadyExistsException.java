package com.mirachi.exception;

@SuppressWarnings("serial")
public class BusinessSettingAlreadyExistsException extends RuntimeException {

    public BusinessSettingAlreadyExistsException(String message) {
	super(message);
    }

}
