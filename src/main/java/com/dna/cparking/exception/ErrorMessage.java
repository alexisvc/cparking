package com.dna.cparking.exception;

import org.springframework.http.HttpStatus;

public class ErrorMessage{

	private final String message;
	private final String detail;
	private final HttpStatus httpStatus;
	
	public ErrorMessage(String message, String detail, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.detail = detail;
		this.httpStatus = httpStatus;		
	}
	
	public ErrorMessage(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.detail = null;
	}

	public String getMessage() {
		return message;
	}
	
	public String getDetail() {
		return detail;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
