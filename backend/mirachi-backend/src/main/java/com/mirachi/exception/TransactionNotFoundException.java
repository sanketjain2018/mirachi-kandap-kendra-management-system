package com.mirachi.exception;

@SuppressWarnings("serial")
public class TransactionNotFoundException 
			extends RuntimeException{
	
	public TransactionNotFoundException(
					String message) {
		super(message);
	}
}
