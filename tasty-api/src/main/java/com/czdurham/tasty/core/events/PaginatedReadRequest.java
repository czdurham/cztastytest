package com.czdurham.tasty.core.events;

import com.czdurham.tasty.core.util.CzUtils;

public class PaginatedReadRequest extends ReadRequest {
	private Integer page;
	private Integer size;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public boolean pageIsSet() {
		return CzUtils.isPositive(page);
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public boolean sizeIsSet() {
		return CzUtils.isPositive(size);
	}
}
