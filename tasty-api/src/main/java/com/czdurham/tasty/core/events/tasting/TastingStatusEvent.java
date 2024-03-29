package com.czdurham.tasty.core.events.tasting;

import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.TastingStatusDetails;
import com.czdurham.tasty.core.events.StatusEvent;

public class TastingStatusEvent extends StatusEvent<TastingStatusDetails> {
	private UUID key;
	
	protected TastingStatusEvent(UUID key) {
		this.key = key;
	}
	
	public TastingStatusEvent(UUID key, TastingStatusDetails status) {
		super(status);
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
	
	public static TastingStatusEvent notFound(UUID key) {
		return new TastingStatusEvent(key);
	}
}
