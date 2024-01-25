package com.ha.java.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e){
		
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getHttpStatus());
		return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
	}
}
