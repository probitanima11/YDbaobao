package com.ydbaobao.model;

public class Order {
	private int orderId;
	private String orderStatus;
	private Customer customer;
	private int enuri;
	private int realPrice;
	private String orderAddress;
	
	public Order() {
		
	}
	
	public Order(int orderId) {
		this(orderId, null, null, 0, 0, null);
	}

	public Order(int orderId, String orderStatus, Customer customer, int enuri, int realPrice, String orderAddress) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.customer = customer;
		this.enuri = enuri;
		this.realPrice = realPrice;
		this.orderAddress = orderAddress;
	}

	public int getOrderId() {
		return orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getEnuri() {
		return enuri;
	}

	public int getRealPrice() {
		return realPrice;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setEnuri(int enuri) {
		this.enuri = enuri;
	}

	public void setRealPrice(int realPrice) {
		this.realPrice = realPrice;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + enuri;
		result = prime * result + ((orderAddress == null) ? 0 : orderAddress.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + realPrice;
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
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (enuri != other.enuri)
			return false;
		if (orderAddress == null) {
			if (other.orderAddress != null)
				return false;
		} else if (!orderAddress.equals(other.orderAddress))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		if (realPrice != other.realPrice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderStatus=" + orderStatus + ", customer=" + customer + ", enuri="
				+ enuri + ", realPrice=" + realPrice + ", orderAddress=" + orderAddress + "]";
	}
}
