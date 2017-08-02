package com.myorg.infra.exception;

public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6480974252336630710L;
	private String message;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message) {
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
