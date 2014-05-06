package com.czdurham.tasty.core.events;

import com.czdurham.tasty.core.domain.TimestampedEnum;

public class StatusEvent<S extends TimestampedEnum<?>> extends ReadEvent {
	private S status;
	
	protected StatusEvent() {
		setFound(false);
	}
	
	public StatusEvent(S status) {
		this.status = status;
	}
	
	public S getStatus() {
		return status;
	}
}
