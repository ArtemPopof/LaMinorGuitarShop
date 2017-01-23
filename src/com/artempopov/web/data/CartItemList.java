package com.artempopov.web.data;

import java.util.ArrayList;

import com.artempopov.web.beans.GuitarBean;

public class CartItemList extends ArrayList<GuitarBean>{
	
	private int totalPrice;
	private int[] itemsCount;
	
	public CartItemList() {
		super();
		
		totalPrice = 0;
		itemsCount = new int[32];
	}
	
	public boolean add(GuitarBean item) {
		
		totalPrice += item.getPrice();
		itemsCount[item.getId()]++;
		
		boolean output = false;

		if (indexOf(item) == -1) {
			super.add(item);
		} 	
		
		return output;
		
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void removeItem(int itemId) {
		
		for (GuitarBean guitar : this) {
			if (guitar.getId() == itemId) {
				this.remove(guitar);
				totalPrice -= guitar.getPrice() * itemsCount[itemId];
				break;
			}
		}
		itemsCount[itemId] = 0;
	}
	
	public int getItemCount(int index) {
		return itemsCount[index];
	}
	
	public int getItemCount(GuitarBean guitar) {
		return itemsCount[guitar.getId()];
	}
	
	public void increaseItemCount(int itemId) {
		itemsCount[itemId]++;
		totalPrice += get(indexOfItem(itemId)).getPrice();
	}

	public void decreaseItemCount(int itemId) {
		if (itemsCount[itemId]>1) {
			itemsCount[itemId]--;
			totalPrice -= get(indexOfItem(itemId)).getPrice();
		}
	}
	
	public int indexOfItem(GuitarBean guitar) {
		
		for (GuitarBean item : this) {
			if (item.getId() == guitar.getId()) {
				return indexOf(item);
			}
		}
		
		return -1;
	}
	
	public int indexOfItem(int itemId) {
		for (int i = 0; i < size(); i++) {
			GuitarBean guitar = this.get(i);
			if (guitar.getId() == itemId) {
				return i;
			}
		}
		
		return -1;
	}
}
