package com.myorg.infra.api.elements;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.myorg.infra.components.Model;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item extends ResourceSupport {
	private String title;
	private String name;
	private String href;
	private Model summary;
	private JsonSchema schema;

	public Item() {
		super();
	}

	@JsonCreator
	public Item(@JsonProperty("title") String title, @JsonProperty("name") String name,
			@JsonProperty("href") String href, @JsonProperty("summary") Model summary,
			@JsonProperty("schema") JsonSchema schema) {
		super();
		this.title = title;
		this.name = name;
		this.href = href;
		this.summary = summary;
		this.schema = schema;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Model getSummary() {
		return summary;
	}

	public void setSummary(Model model) {
		this.summary = model;
	}

	public JsonSchema getSchema() {
		return schema;
	}

	public void setSchema(JsonSchema schema) {
		this.schema = schema;
	}

}
