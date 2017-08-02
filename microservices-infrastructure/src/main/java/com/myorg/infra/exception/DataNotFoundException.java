package com.myorg.infra.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	String message;

	public DataNotFoundException() {
		super();
	}

}
