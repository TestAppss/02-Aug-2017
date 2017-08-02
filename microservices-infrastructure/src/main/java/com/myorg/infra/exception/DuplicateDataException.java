package com.myorg.infra.exception;

public class DuplicateDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4041689306035714561L;
	private String message;

	public DuplicateDataException() {
		super();
	}

	public DuplicateDataException(String message) {
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
