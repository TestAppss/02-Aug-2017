package com.myorg.infra.api.elements;

import java.util.ArrayList;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Link extends ResourceSupport {
	private ArrayList<Item> items = new ArrayList<Item>();

	public Link() {
		super();
	}

	public ArrayList<Item> getItem() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@JsonCreator
	public Link(@JsonProperty("item") ArrayList<Item> items) {
		super();
		this.items = items;
	}

}
