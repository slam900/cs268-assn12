package edu.uwec.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
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
	
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Candy Product [id=" + id + ", description=" + description + ", price=" + price + "]";
	}
}
