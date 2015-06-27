package com.ydbaobao.model;

public class Item {
	private int itemId;
	private Customer customer;
	private Product product;
	private String itemSize;
	private int itemQuantity;
	private String itemStatus;
	/**
	 * 카트 : "I"
	 * 주문 : "S"
	 * 취소 : "C"
	 * 반려 : "R"
	 */
	
	public Item() {
		
	}
	
	public Item(int itemId) {
		this(itemId, null, null, null, 0, "");
	}

	public Item(int itemId, Customer customer, Product product, String itemSize, int itemQuantity, String itemStatus) {
		this.itemId = itemId;
		this.customer = customer;
		this.product = product;
		this.itemSize = itemSize;
		this.itemQuantity = itemQuantity;
		this.itemStatus = itemStatus;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + itemId;
		result = prime * result + itemQuantity;
		result = prime * result
				+ ((itemSize == null) ? 0 : itemSize.hashCode());
		result = prime * result
				+ ((itemStatus == null) ? 0 : itemStatus.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (itemId != other.itemId)
			return false;
		if (itemQuantity != other.itemQuantity)
			return false;
		if (itemSize == null) {
			if (other.itemSize != null)
				return false;
		} else if (!itemSize.equals(other.itemSize))
			return false;
		if (itemStatus == null) {
			if (other.itemStatus != null)
				return false;
		} else if (!itemStatus.equals(other.itemStatus))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", customer=" + customer
				+ ", product=" + product + ", itemSize=" + itemSize
				+ ", itemQuantity=" + itemQuantity + ", itemStatus="
				+ itemStatus + "]";
	}
}
