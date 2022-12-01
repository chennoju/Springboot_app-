package com.project.myapp.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItem {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("discountPercentage")
	private String discountPercentage;
	
	@JsonProperty("rating")
	private String rating;
	
	@JsonProperty("stock")
	private String stock;
	
	@JsonProperty("brand")
	private String brand;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("thumbnail")
	private String thumbnail;
	
	@JsonProperty("images")
	private  List<String> images;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", discountPercentage=" + discountPercentage + ", rating=" + rating + ", stock=" + stock + ", brand="
				+ brand + ", category=" + category + ", thumbnail=" + thumbnail + ", images=" + images
				+ "]";
	}
	
}
