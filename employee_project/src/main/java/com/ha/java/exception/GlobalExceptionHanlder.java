package com.ha.java.exception;

import com.ha.java.dto.ErrorDetails;
import com.ha.java.exception.argument.ErrorsArgument;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHanlder extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            System.out.println(((FieldError) error).getField());
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });

        ErrorsArgument errors = new ErrorsArgument();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    if (((FieldError) error).getField().equals("firstName"))
                        errors.setFirstName(error.getDefaultMessage());
                    else if (((FieldError) error).getField().equals("lastName"))
                        errors.setLastName(error.getDefaultMessage());
                    else if (((FieldError) error).getField().equals("phoneNumber"))
                        errors.setPhoneNumber(error.getDefaultMessage());
                    else if (((FieldError) error).getField().equals("email"))
                        errors.setEmail(error.getDefaultMessage());
                }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
