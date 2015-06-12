package com.ydbaobao.model;

public class Category {
	private int categoryId;
	private String categoryName;
	private int categoryCount;
	
	public Category() {
	}
	
	public Category(int categoryId) {
		this(categoryId, null, 0);
	}
	
	public Category(int categoryId, String categoryName, int categoryCount) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryCount = categoryCount;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public int getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCategoryCount(int categoryCount) {
		this.categoryCount = categoryCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryCount;
		result = prime * result + categoryId;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
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
		Category other = (Category) obj;
		if (categoryCount != other.categoryCount)
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryCount="
				+ categoryCount + "]";
	}
}