package com.myorg.infra.api.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel extends ResourceSupport {

	private List<MessageModel> messages = new ArrayList<MessageModel>();
	private String outcome;

	@JsonCreator
	public ResponseModel(List<MessageModel> messages, String outcome) {
		super();
		this.messages = messages;
		this.outcome = outcome;
	}

	@JsonCreator
	public ResponseModel() {
		super();
	}

	public List<MessageModel> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageModel> messages) {
		this.messages = messages;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
}
