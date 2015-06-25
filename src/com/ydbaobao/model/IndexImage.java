package com.ydbaobao.model;

public class IndexImage {
	private int indexImageId;
	private String indexImageName;
	public IndexImage() {
	}
	public IndexImage(int indexImageId, String indexImageName) {
		this.indexImageId = indexImageId;
		this.indexImageName = indexImageName;
	}
	public int getIndexImageId() {
		return indexImageId;
	}
	public void setIndexImageId(int indexImageId) {
		this.indexImageId = indexImageId;
	}
	public String getIndexImageName() {
		return indexImageName;
	}
	public void setIndexImageName(String indexImageName) {
		this.indexImageName = indexImageName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + indexImageId;
		result = prime * result
				+ ((indexImageName == null) ? 0 : indexImageName.hashCode());
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
		IndexImage other = (IndexImage) obj;
		if (indexImageId != other.indexImageId)
			return false;
		if (indexImageName == null) {
			if (other.indexImageName != null)
				return false;
		} else if (!indexImageName.equals(other.indexImageName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IndexImage [indexImageId=" + indexImageId + ", indexImageName="
				+ indexImageName + "]";
	}
}
