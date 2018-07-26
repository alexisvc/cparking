package com.dna.cparking.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler{	
	
	@ExceptionHandler(ApiErrorBuilderException.class)
	protected ResponseEntity<Object> handleInvalidOperation(ApiErrorBuilderException exception) {
		return new ResponseEntity<>( exception.getMessage(), exception.getHttpStatus());
	}
}
