package com.myorg.infra.api.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Collection extends ResourceSupport {

	private List<Item> items = new ArrayList<Item>();

	@JsonCreator
	public Collection() {
		super();
	}

	@JsonCreator
	public Collection(ArrayList<Item> items) {
		super();
		this.items = items;

	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
