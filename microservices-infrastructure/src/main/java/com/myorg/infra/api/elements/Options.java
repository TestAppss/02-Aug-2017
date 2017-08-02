package com.myorg.infra.api.elements;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Options {

	private ArrayList<OptionsLink> links = new ArrayList<OptionsLink>();

	public Options() {
		super();
	}

	@JsonCreator
	public Options(@JsonProperty("link") ArrayList<OptionsLink> links) {
		super();
		this.setLinks(links);
	}

	public ArrayList<OptionsLink> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<OptionsLink> links) {
		this.links = links;
	}

}
