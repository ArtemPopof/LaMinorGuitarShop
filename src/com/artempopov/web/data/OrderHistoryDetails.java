package com.artempopov.web.data;

import java.util.ArrayList;

public class OrderHistoryDetails {

	private ArrayList<Item> items;
	
	private Order order;
	
	public OrderHistoryDetails(ArrayList<Item> items, Order order) {
		this.items = (ArrayList<Item>) items.clone();
		this.order = order;
	}
	
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


}
