package com.ydbaobao.model;

import java.util.ArrayList;
import java.util.List;

public class Brand {
	private int brandId;
	private String brandName;
	private int brandCount;
	
	public Brand() {
	}

	public Brand(int brandId, String brandName, int brandCount) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.brandCount = brandCount;
	}
	
	public List<Character> getFirstLetters() {
		List<Character> firstLetters = new ArrayList<Character>();
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			firstLetters.add(ch);	
		}
		return firstLetters;
	}

	public int getBrandId() {
		return brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public int getBrandCount() {
		return brandCount;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setBrandCount(int brandCount) {
		this.brandCount = brandCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brandCount;
		result = prime * result + brandId;
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
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
		Brand other = (Brand) obj;
		if (brandCount != other.brandCount)
			return false;
		if (brandId != other.brandId)
			return false;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", brandName=" + brandName + ", brandCount=" + brandCount + "]";
	}
}