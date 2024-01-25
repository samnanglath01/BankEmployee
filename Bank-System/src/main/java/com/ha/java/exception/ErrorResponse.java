package com.ha.java.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private String message;
	
	private HttpStatus httpStatus;
	
}
