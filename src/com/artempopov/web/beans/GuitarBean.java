package com.artempopov.web.beans;

import java.io.Serializable;
import java.util.Random;

import com.artempopov.web.Translator;

public class GuitarBean implements Serializable{

	private static final long serialVersionUID = 4084458460144776581L;
	
	private static int nextId = 0;
	
	private String vendorCode;
	private String name;
	private String type;
	private String pictureURL;
	private String available;

	private String pickupsInfo;
	private String manufacturer;
	private String country;
	private String color;
	
	private String reviewRu;
	private String reviewEn;
	private String reviewGr;

	// in USD
	private int productPrice;

	private int itemId;
	
	public GuitarBean(String productName, String productType, String vendorCode, int productPrice, String pictureImageURL) {
		
		this.vendorCode = vendorCode;
		this.name = productName;
		this.type = productType;
		this.pictureURL = pictureImageURL;
		this.productPrice = productPrice;
		available = "Available";
		
		itemId = generateId();
		
		reviewRu = "Для данного товара описание еще не готово.";
		reviewEn = "This product's discription is not ready yet.";
		reviewGr = "πειραματισμό με τον ήχο.";
		
		pickupsInfo = "Unavailable";
		manufacturer = "Unknown";
		country = "Unknown";
		color = "Unavailable";
	}
	
	// getters and setters
	
	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String productName) {
		this.name = productName;
	}

	public String getType() {
		return type;
	}

	public void setType(String productType) {
		this.type = productType;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureImageURL) {
		this.pictureURL = pictureImageURL;
	}

	public int getPrice() {
		return productPrice;
	}

	public void setPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	public void setAvailalbeStatus(String available) {
		this.available = available;
	}
	
	public String getAvailableStatus() {
		return this.available;
	}
	
	public static int generateId() {
		nextId++;
		
		return nextId;
	}
	
	public int getId() {
		return itemId;
	}
	
	public void setReview(String review, String locale) {
		if (locale.equals("ru")) {
			this.reviewRu = review;
		} else if (locale.equals("en")) {
			this.reviewEn = review;
		} else if (locale.equals("gr")) {
			this.reviewGr = review;
		} else {
			System.out.println("Trying to set Guitar Review with unknown locale");
		}
	}
	
	public String getReview(Translator translator) {
		String lang = translator.getLang();
		
		if (lang.equals("ru")) {
			return reviewRu;
		} else if (lang.equals("en")) {
			return reviewEn;
		} else if (lang.equals("gr")) {
			return reviewGr;
		}
		
		System.out.println("Cannon give guitar review in locale: "+lang);
		return reviewEn;
		
	}
	
	public String getPickupsInfo() {
		return pickupsInfo;
	}

	public void setPickupsInfo(String pickupsInfo) {
		this.pickupsInfo = pickupsInfo;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

}
