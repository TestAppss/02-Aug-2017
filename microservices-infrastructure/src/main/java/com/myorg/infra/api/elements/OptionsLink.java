package com.myorg.infra.api.elements;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionsLink {
	private String title;
	private String method;
	private String rel;
	private String href;
	private JsonSchema schema;

	private List<Parameters> parameters;

	public OptionsLink() {
		super();
	}

	@JsonCreator
	public OptionsLink(@JsonProperty("title") String title, @JsonProperty("method") String method,
			@JsonProperty("href") String href, @JsonProperty("rel") String rel,
			@JsonProperty("schema") JsonSchema schema, @JsonProperty("parameters") List<Parameters> parameters) {
		super();
		this.title = title;
		this.method = method;
		this.rel = rel;
		this.href = href;
		this.schema = schema;

		this.parameters = parameters;
	}

	public JsonSchema getSchema() {
		return schema;
	}

	public void setSchema(JsonSchema schema) {
		this.schema = schema;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Parameters> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameters> parameters) {
		this.parameters = parameters;
	}
}
