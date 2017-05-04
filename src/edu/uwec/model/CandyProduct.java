package edu.uwec.model;

import java.io.Serializable;

public class CandyProduct implements Serializable {
	static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private double price;
	
	public CandyProduct() {}

	public CandyProduct(int id, String description, double price) {
		this.id = id;
		this.description = description;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
