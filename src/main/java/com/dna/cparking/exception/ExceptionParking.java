package com.dna.cparking.exception;

public class ExceptionParking extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final String message;

	public ExceptionParking(String message) {
//		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
