package com.mirachi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mirachi.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(
	            UserNotFoundException ex) {

	        ApiResponse<Object> response =
	                new ApiResponse<>(false, ex.getMessage(), null);

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(InvalidCredentialsException.class)
	    public ResponseEntity<ApiResponse<Object>> handleInvalidCredentials(
	            InvalidCredentialsException ex) {

	        ApiResponse<Object> response =
	                new ApiResponse<>(false, ex.getMessage(), null);

	        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponse<Object>> handleGenericException(
	            Exception ex) {

	        ApiResponse<Object> response =
	                new ApiResponse<>(false, ex.getMessage(), null);

	        return new ResponseEntity<>(response,
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
