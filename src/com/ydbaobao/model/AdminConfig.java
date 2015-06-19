package com.ydbaobao.model;

public class AdminConfig {
	private int adminConfigId;
	private int adminDisplayProducts;
	private String adminPassword;
	
	public AdminConfig(){};
	
	public AdminConfig(int adminConfigId, int adminDisplayProducts, String adminPassword) {
		this.adminConfigId = adminConfigId;
		this.adminDisplayProducts = adminDisplayProducts;
		this.adminPassword = adminPassword;
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

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminConfigId;
		result = prime * result + adminDisplayProducts;
		result = prime * result
				+ ((adminPassword == null) ? 0 : adminPassword.hashCode());
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
		if (adminPassword == null) {
			if (other.adminPassword != null)
				return false;
		} else if (!adminPassword.equals(other.adminPassword))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdminConfig [adminConfigId=" + adminConfigId
				+ ", adminDisplayProducts=" + adminDisplayProducts
				+ ", adminPassword=" + adminPassword + "]";
	}
}
