package com.myorg.infra.api.elements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageModel {
	private String message;
	private String Severity;

	@JsonCreator
	public MessageModel() {
		super();
	}

	@JsonCreator
	public MessageModel(String severity, String message) {
		super();
		Severity = severity;
		this.message = message;
	}

	public String getSeverity() {
		return Severity;
	}

	public void setSeverity(String severity) {
		Severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
