package com.czdurham.tasty.core.events.tasting;

import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.CreatedEvent;

public class TastingCreatedEvent extends CreatedEvent {
	private final UUID key;
	private final TastingDetails tastingDetails;

	public TastingCreatedEvent(UUID key, TastingDetails tastingDetails) {
		this.key = key;
		this.tastingDetails = tastingDetails;
	}

	public UUID getKey() {
		return key;
	}

	public TastingDetails getTastingDetails() {
		return tastingDetails;
	}
}