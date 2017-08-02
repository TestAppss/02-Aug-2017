package com.myorg.infra.api.elements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Parameters {

	private String in;
	private String name;
	private String type;
	private boolean required;
	private String description;

	public Parameters() {
		super();
	}

	@JsonCreator
	public Parameters(@JsonProperty("in") String in, @JsonProperty("name") String name,
			@JsonProperty("type") String type, @JsonProperty("required") boolean required,
			@JsonProperty("description") String description) {
		super();
		this.in = in;
		this.name = name;
		this.type = type;
		this.required = required;
		this.description = description;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
