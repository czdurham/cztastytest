package com.czdurham.tasty.core.events;

public class PaginatedReadEvent extends ReadEvent {
	private int page;
	private int size;
	private int total;
	
	public PaginatedReadEvent(int page, int size, int total) {
		this.page = page;
		this.size = size;
		this.total = total;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getTotal() {
		return total;
	}
}