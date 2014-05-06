package com.czdurham.tasty.core.events.taster;

import java.util.UUID;

import com.czdurham.tasty.core.domain.taster.TasterDetails;
import com.czdurham.tasty.core.events.ReadEvent;

public class TasterDetailsEvent extends ReadEvent {
	private final UUID key;
	private TasterDetails tasterDetails;

	protected TasterDetailsEvent(UUID key) {
		this.key = key;
		setFound(false);
	}

	public TasterDetailsEvent(UUID key, TasterDetails tasterDetails) {
		this.key = key;
		this.tasterDetails = tasterDetails;
	}

	public UUID getKey() {
		return key;
	}

	public TasterDetails getTasterDetails() {
		return tasterDetails;
	}

	public static TasterDetailsEvent notFound(UUID key) {
		return new TasterDetailsEvent(key);
	}
}
