package com.mirachi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;

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
	    
	    @ExceptionHandler(CustomerNotFoundException.class)
	    public ResponseEntity<ApiResponse<Object>> handleCustomerNotFound(CustomerNotFoundException ex){
	    	
	    	ApiResponse<Object> response =
	                new ApiResponse<>(
	                        false,
	                        ex.getMessage(),
	                        null);
	    	
	    	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(DuplicateCustomerException.class)
	    public ResponseEntity<ApiResponse<Object>>
	    handleDuplicateCustomer(
	            DuplicateCustomerException ex) {

	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body(new ApiResponse<>(
	                        false,
	                        ex.getMessage(),
	                        null));
	    }
	    
	    @ExceptionHandler(
	            MethodArgumentNotValidException.class)
	    public ResponseEntity<ApiResponse<Map<String,String>>>
	    handleValidationException(
	            MethodArgumentNotValidException ex) {

	        Map<String,String> errors =
	                new HashMap<>();

	        ex.getBindingResult()
	                .getFieldErrors()
	                .forEach(error ->
	                        errors.put(
	                                error.getField(),
	                                error.getDefaultMessage()));

	        return ResponseEntity.badRequest()
	                .body(new ApiResponse<>(
	                        false,
	                        "Validation Failed",
	                        errors));
	    }
	    
	    @ExceptionHandler(BillNotFoundException.class)
	    public ResponseEntity<ApiResponse<String>>
	    handleBillNotFoundException(
	            BillNotFoundException ex) {

	        return ResponseEntity.status(
	                HttpStatus.NOT_FOUND)
	                .body(
	                        new ApiResponse<>(
	                                false,
	                                ex.getMessage(),
	                                null));
	    }
	    
	    
	    @ExceptionHandler(
	            ExpenseNotFoundException.class)
	    public ResponseEntity<ApiResponse<?>>
	    handleExpenseNotFoundException(
	            ExpenseNotFoundException ex) {

	        return ResponseEntity.status(
	                HttpStatus.NOT_FOUND)
	                .body(
	                        new ApiResponse<>(
	                                false,
	                                ex.getMessage(),
	                                null));
	    }
	    
	    public ResponseEntity<ApiResponse<?>>
	    handleAdminNotFoundException(
		    AdminNotFoundException ex){
		return ResponseEntity.status(
			HttpStatus.NOT_FOUND)
			.body(
				new ApiResponse<>(
					false,
					ex.getMessage(),
					null));
	    }
}




























