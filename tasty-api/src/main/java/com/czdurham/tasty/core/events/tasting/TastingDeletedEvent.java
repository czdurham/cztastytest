package com.czdurham.tasty.core.events.tasting;

import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.DeletedEvent;

public class TastingDeletedEvent extends DeletedEvent {
	private final UUID key;
	private TastingDetails tastingDetails;
	private boolean deleted = true;

	protected TastingDeletedEvent(UUID key) {
		this.key = key;
		setFound(false);
	}

	public TastingDeletedEvent(UUID key, TastingDetails tastingDetails) {
		this.key = key;
		this.tastingDetails = tastingDetails;
	}

	public UUID getKey() {
		return key;
	}

	public TastingDetails getTastingDetails() {
		return tastingDetails;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public static TastingDeletedEvent notFound(UUID key) {
		return new TastingDeletedEvent(key);
	}

	public static TastingDeletedEvent forbidden(UUID key, TastingDetails tastingDetails) {
		TastingDeletedEvent event = new TastingDeletedEvent(key, tastingDetails);
		event.deleted = false;
		return event;
	}
}
