package com.dna.cparking.exception.type;

import org.springframework.http.HttpStatus;

public class UnabledOperationException extends Exception {

	private static final long serialVersionUID = 811289691161720122L;

	private final String message;
	private final HttpStatus httpStatus;

	public UnabledOperationException(String message, HttpStatus httpStatus) {
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
