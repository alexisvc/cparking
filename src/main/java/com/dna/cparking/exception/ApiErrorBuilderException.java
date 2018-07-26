package com.dna.cparking.exception;

import org.springframework.http.HttpStatus;

public class ApiErrorBuilderException extends Exception{

	private static final long serialVersionUID = 5699533229660938579L;
	
	private final String message;
	private final HttpStatus httpStatus;
	
	public ApiErrorBuilderException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
