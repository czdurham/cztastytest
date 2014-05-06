package com.czdurham.tasty.core.events.tasting;

import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.ReadEvent;

public class TastingDetailsEvent extends ReadEvent {
	private final UUID key;
	private TastingDetails tastingDetails;

	protected TastingDetailsEvent(UUID key) {
		this.key = key;
		setFound(false);
	}

	public TastingDetailsEvent(UUID key, TastingDetails tastingDetails) {
		this.key = key;
		this.tastingDetails = tastingDetails;
	}

	public UUID getKey() {
		return key;
	}

	public TastingDetails getTastingDetails() {
		return tastingDetails;
	}

	public static TastingDetailsEvent notFound(UUID key) {
		return new TastingDetailsEvent(key);
	}
}
