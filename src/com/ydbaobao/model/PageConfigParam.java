package com.ydbaobao.model;

public class PageConfigParam {
	private int quantity;
	private int index = 0;
	private int selectedIndex = 0;
	private int start=0;
	private int end=0;
	private int range;
	
	public PageConfigParam (int quantity, String index, int productsCount){
		int tempIndex=0;
		if (null != index) {
			tempIndex = Integer.parseInt(index);
			this.selectedIndex = tempIndex-1;
			tempIndex = (tempIndex-1)*quantity;
		}
		
		this.range = productsCount/quantity;
		if (productsCount%quantity > 0) {
			this.range++;
		}
		this.end = this.range;
		if (this.range > 10) {
			this.start = tempIndex/quantity/10*10;
		}
		if (this.range > this.start+10) {
			this.end = this.start+10;
		}
		if (this.end == this.start) {
			this.end++;
		}
		this.index = tempIndex;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getIndex() {
		return index;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getRange() {
		return range;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setRange(int range) {
		this.range = range;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + index;
		result = prime * result + quantity;
		result = prime * result + range;
		result = prime * result + selectedIndex;
		result = prime * result + start;
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
		PageConfigParam other = (PageConfigParam) obj;
		if (end != other.end)
			return false;
		if (index != other.index)
			return false;
		if (quantity != other.quantity)
			return false;
		if (range != other.range)
			return false;
		if (selectedIndex != other.selectedIndex)
			return false;
		if (start != other.start)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PageConfigParam [quantity=" + quantity + ", index=" + index + ", selectedIndex=" + selectedIndex
				+ ", start=" + start + ", end=" + end + ", range=" + range + "]";
	}
	
}
