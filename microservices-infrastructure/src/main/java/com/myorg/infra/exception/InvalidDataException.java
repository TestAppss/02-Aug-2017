package com.myorg.infra.exception;

public class InvalidDataException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3498559508239775194L;
	private String message;

	public InvalidDataException() {
		super();
	}

	public InvalidDataException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
