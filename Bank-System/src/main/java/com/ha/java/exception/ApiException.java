package com.ha.java.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{

	private HttpStatus httpStatus;
	
	private String message;
	
	
	
}
