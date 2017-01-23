package com.artempopov.web.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATALOG")
public class OrderHistory implements Serializable{

	@Column(name="ORDER_ID")
	@Id
	private long orderId;
	
	@Column(name="ITEM_ID")
	@Id
	private long itemId;

	@Column(name="ITEM_COUNT")
	private long itemCount;
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}


}
