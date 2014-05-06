package com.czdurham.tasty.core.events.taster;

import java.util.UUID;

import com.czdurham.tasty.core.domain.taster.TasterDetails;
import com.czdurham.tasty.core.events.DeletedEvent;

public class TasterDeletedEvent extends DeletedEvent {
	private final UUID key;
	private TasterDetails tasterDetails;
	private boolean deleted = true;

	protected TasterDeletedEvent(UUID key) {
		this.key = key;
		setFound(false);
	}

	public TasterDeletedEvent(UUID key, TasterDetails tasterDetails) {
		this.key = key;
		this.tasterDetails = tasterDetails;
	}

	public UUID getKey() {
		return key;
	}

	public TasterDetails getTasterDetails() {
		return tasterDetails;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public static TasterDeletedEvent notFound(UUID key) {
		return new TasterDeletedEvent(key);
	}

	public static TasterDeletedEvent forbidden(UUID key, TasterDetails tasterDetails) {
		TasterDeletedEvent event = new TasterDeletedEvent(key, tasterDetails);
		event.deleted = false;
		return event;
	}
}
