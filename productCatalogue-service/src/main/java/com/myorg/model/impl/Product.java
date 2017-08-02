package com.myorg.model.impl;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.hateoas.ResourceSupport;
import com.myorg.infra.components.Model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends ResourceSupport implements Model {

	// Start here
	protected int prodId;
	protected String name;
	protected String type;
	protected BigDecimal price;

	
	@JsonCreator
	public Product() {
		super();
	}

	public Product(int prodId, String name, String type, BigDecimal price) {
		super();
		this.prodId = prodId;
		this.name = name;
		this.type = type;
		this.price = price;

	}
	public String getName() {
		return name;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String generateIdFromModel() {
		// TODO Auto-generated method stub
		return String.valueOf(prodId);
	}

	@Override
	public void setupModelFromId(String prodId) {
		// TODO Auto-generated method stub

		int prodIdstr = Integer.parseInt(prodId);
		this.prodId = prodIdstr;

	}

}
