package com.myorg.infra.exception;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationError {
	@JsonProperty("errors")
	private List<FieldError> fieldErrors = new ArrayList<FieldError>();

	public ValidationError() {

	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public void addFieldError(String field, String message) {
		FieldError fieldError = new FieldError(field, message);
		fieldErrors.add(fieldError);
	}
}
