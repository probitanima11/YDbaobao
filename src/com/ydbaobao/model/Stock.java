package com.ydbaobao.model;

public class Stock {
	private int stockId;
	private Product product;
	private String size;
	private int quantity;
	
	public Stock(int stockId, Product product, String size, int quantity) {
		super();
		this.stockId = stockId;
		this.product = product;
		this.size = size;
		this.quantity = quantity;
	}

	public int getStockId() {
		return stockId;
	}

	public Product getProduct() {
		return product;
	}

	public String getSize() {
		return size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + stockId;
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
		Stock other = (Stock) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (stockId != other.stockId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", product=" + product + ", size=" + size + ", quantity=" + quantity + "]";
	}
}
