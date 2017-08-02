package com.myorg.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myorg.infra.components.JPAEntity;

/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@Table(name = "PRODUCT")
public class ProductTo extends JPAEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRDID")
	private int prodId;
	
	@Column(name = "PRDNAME")
	private String name;

	@Column(name = "PRDTYPE")
	private String type;

	@Column(name = "PRDPRICE")
	private String price;
	
	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
		
}