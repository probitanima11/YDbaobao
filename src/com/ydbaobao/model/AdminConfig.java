package com.ydbaobao.model;

public class AdminConfig {
	private int adminConfigId;
	private int adminDisplayProducts;
	
	public AdminConfig(){};
	
	public AdminConfig(int adminConfigId, int adminDisplayProducts) {
		this.adminConfigId = adminConfigId;
		this.adminDisplayProducts = adminDisplayProducts;
	}

	public int getAdminConfigId() {
		return adminConfigId;
	}

	public void setAdminConfigId(int adminConfigId) {
		this.adminConfigId = adminConfigId;
	}

	public int getAdminDisplayProducts() {
		return adminDisplayProducts;
	}

	public void setAdminDisplayProducts(int adminDisplayProducts) {
		this.adminDisplayProducts = adminDisplayProducts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminConfigId;
		result = prime * result + adminDisplayProducts;
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
		AdminConfig other = (AdminConfig) obj;
		if (adminConfigId != other.adminConfigId)
			return false;
		if (adminDisplayProducts != other.adminDisplayProducts)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdminConfig [adminConfigId=" + adminConfigId
				+ ", adminDisplayProducts=" + adminDisplayProducts + "]";
	}
}
