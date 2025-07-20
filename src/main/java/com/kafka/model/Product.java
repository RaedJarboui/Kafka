package com.kafka.model;

public class Product {
	
	private int productId;
	private String name;
	private float price;
	
	public Product() {
		
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Product(int productId, String name, float price) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}
	
	
	
	
	

}
