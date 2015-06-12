package com.ydbaobao.model;

import java.util.ArrayList;
import java.util.List;

public class Brand {
	private int brandId;
	private String brandName;
	private int brandCount;
	private int discount_1;
	private int discount_2;
	private int discount_3;
	private int discount_4;
	private int discount_5;
	
	public Brand() {
	}

	public Brand(int brandId, String brandName, int brandCount, int discount_1, int discount_2, int discount_3, int discount_4, int discount_5) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.brandCount = brandCount;
		this.discount_1 = discount_1;
		this.discount_2 = discount_2;
		this.discount_3 = discount_3;
		this.discount_4 = discount_4;
		this.discount_5 = discount_5;
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

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getBrandCount() {
		return brandCount;
	}

	public void setBrandCount(int brandCount) {
		this.brandCount = brandCount;
	}

	public int getDiscount_1() {
		return discount_1;
	}

	public void setDiscount_1(int discount_1) {
		this.discount_1 = discount_1;
	}

	public int getDiscount_2() {
		return discount_2;
	}

	public void setDiscount_2(int discount_2) {
		this.discount_2 = discount_2;
	}

	public int getDiscount_3() {
		return discount_3;
	}

	public void setDiscount_3(int discount_3) {
		this.discount_3 = discount_3;
	}

	public int getDiscount_4() {
		return discount_4;
	}

	public void setDiscount_4(int discount_4) {
		this.discount_4 = discount_4;
	}

	public int getDiscount_5() {
		return discount_5;
	}

	public void setDiscount_5(int discount_5) {
		this.discount_5 = discount_5;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brandCount;
		result = prime * result + brandId;
		result = prime * result
				+ ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + discount_1;
		result = prime * result + discount_2;
		result = prime * result + discount_3;
		result = prime * result + discount_4;
		result = prime * result + discount_5;
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
		if (discount_1 != other.discount_1)
			return false;
		if (discount_2 != other.discount_2)
			return false;
		if (discount_3 != other.discount_3)
			return false;
		if (discount_4 != other.discount_4)
			return false;
		if (discount_5 != other.discount_5)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", brandName=" + brandName
				+ ", brandCount=" + brandCount + ", discount_1=" + discount_1
				+ ", discount_2=" + discount_2 + ", discount_3=" + discount_3
				+ ", discount_4=" + discount_4 + ", discount_5=" + discount_5
				+ "]";
	}
}