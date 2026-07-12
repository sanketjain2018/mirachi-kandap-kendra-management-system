package com.mirachi.exception;

@SuppressWarnings("serial")
public class ExpenseNotFoundException extends RuntimeException {
	
	public ExpenseNotFoundException(
			String message) {
		super(message);
	}
	
}
