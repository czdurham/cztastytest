package com.czdurham.tasty.core.events;

public class ExistingEvent {
	private boolean found = true;

	public boolean isFound() {
		return found;
	}
	
	protected void setFound(boolean found) {
		this.found = found;
	}
}
