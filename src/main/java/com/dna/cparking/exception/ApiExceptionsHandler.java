package com.dna.cparking.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dna.cparking.exception.type.UnabledOperationException;
import com.dna.cparking.message.CatalogMessage;

@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {	
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorMessage> handleGeneralException (Exception exception) {
		ErrorMessage exceptionResponse = new ErrorMessage(CatalogMessage.GENERAL_ERROR, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), exceptionResponse.getHttpStatus());
	}	

	@ExceptionHandler(UnabledOperationException.class)
	protected ResponseEntity<ErrorMessage> handleInvalidOperation (UnabledOperationException exception) {
		ErrorMessage exceptionResponse = new ErrorMessage(exception.getMessage(), exception.getHttpStatus());
		return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), exceptionResponse.getHttpStatus());
	}
}
