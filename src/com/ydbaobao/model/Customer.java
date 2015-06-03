package com.ydbaobao.model;

public class Customer {
	private String customerId;
	private String customerName;
	private String customerPassword;
	private String customerGrade;
	private String customerPhone;
	private String customerEmail;
	private String customerAddress;
	
	public Customer() {
		
	}
	
	

	public Customer(String customerId) {
		this(customerId, null, null, null, null, null, null);
	}



	public Customer(String customerId, String customerName, String customerPassword, String customerPhone, String customerEmail, String customerAddress) {
		this(customerId, customerName, customerPassword, null, customerPhone, customerEmail, customerAddress);
	}
	
	public Customer(String customerId, String customerName, String customerPassword, String customerGrade,
			String customerPhone, String customerEmail, String customerAddress) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerGrade = customerGrade;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public String getCustomerGrade() {
		return customerGrade;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	public boolean isCorrectPassword(String customerPassword) {
		return this.customerPassword.equals(customerPassword);
	}
	
	public SessionCustomer createSessionCustomer() {
		return new SessionCustomer(customerId, customerName);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerEmail == null) ? 0 : customerEmail.hashCode());
		result = prime * result + ((customerGrade == null) ? 0 : customerGrade.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((customerPassword == null) ? 0 : customerPassword.hashCode());
		result = prime * result + ((customerPhone == null) ? 0 : customerPhone.hashCode());
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
		Customer other = (Customer) obj;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerEmail == null) {
			if (other.customerEmail != null)
				return false;
		} else if (!customerEmail.equals(other.customerEmail))
			return false;
		if (customerGrade == null) {
			if (other.customerGrade != null)
				return false;
		} else if (!customerGrade.equals(other.customerGrade))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerPassword == null) {
			if (other.customerPassword != null)
				return false;
		} else if (!customerPassword.equals(other.customerPassword))
			return false;
		if (customerPhone == null) {
			if (other.customerPhone != null)
				return false;
		} else if (!customerPhone.equals(other.customerPhone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", customerGrade=" + customerGrade + ", customerPhone=" + customerPhone
				+ ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress + "]";
	}
}
