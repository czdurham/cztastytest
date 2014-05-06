package com.czdurham.tasty.core.events.tasting;

import java.util.UUID;

import com.czdurham.tasty.core.domain.taster.TasterDetails;

public class TastingTasterCreatedEvent {
	private final UUID key;
	private final TasterDetails tasterDetails;

	public TastingTasterCreatedEvent(UUID key, TasterDetails tasterDetails) {
		this.key = key;
		this.tasterDetails = tasterDetails;
	}

	public UUID getKey() {
		return key;
	}

	public TasterDetails getTasterDetails() {
		return tasterDetails;
	}
}