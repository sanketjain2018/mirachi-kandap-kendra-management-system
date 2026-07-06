package com.mirachi.exception;

@SuppressWarnings("serial")
public class DuplicateCustomerException extends RuntimeException {
	
	public DuplicateCustomerException(String message) {
		super(message);
	}
}
