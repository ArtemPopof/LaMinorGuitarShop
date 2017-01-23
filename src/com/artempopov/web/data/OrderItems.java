package com.artempopov.web.data;

import java.util.ArrayList;

import javax.persistence.Column;

import com.artempopov.web.beans.GuitarBean;

public class OrderItems {

	private long orderId;
	private ArrayList<GuitarBean> guitars;
	
	public OrderItems(ArrayList<GuitarBean> guitars,long id) {
		this.orderId = id;
		this.guitars = guitars;
	}
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public ArrayList<GuitarBean> getGuitars() {
		return guitars;
	}
	public void setGuitars(ArrayList<GuitarBean> guitars) {
		this.guitars = guitars;
	}

}
