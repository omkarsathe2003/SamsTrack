package com.tka.sams.api.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DivideByZeroException.class)
	public ResponseEntity<ErrorResponse> divideByZeroException(DivideByZeroException ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(500);
		errorResponse.setPath(request.getRequestURI());
		
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFoundException(
	        ResourceNotFoundException ex,
	        HttpServletRequest request) {

	    ErrorResponse errorResponse = new ErrorResponse();

	    errorResponse.setMessage(ex.getMessage());
	    errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	    errorResponse.setPath(request.getRequestURI());
	    errorResponse.setTimestamp(java.time.LocalDateTime.now());

	    return new ResponseEntity<>(errorResponse,
	            HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(
	        Exception ex,
	        HttpServletRequest request) {

	    ErrorResponse errorResponse = new ErrorResponse();

	    errorResponse.setMessage(ex.getMessage());
	    errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	    errorResponse.setPath(request.getRequestURI());
	    errorResponse.setTimestamp(java.time.LocalDateTime.now());

	    return new ResponseEntity<>(errorResponse,
	            HttpStatus.INTERNAL_SERVER_ERROR);
	}

}