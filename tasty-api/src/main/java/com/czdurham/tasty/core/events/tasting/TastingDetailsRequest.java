package com.czdurham.tasty.core.events.tasting;

import java.util.UUID;

import com.czdurham.tasty.core.events.ReadRequest;

public class TastingDetailsRequest extends ReadRequest {
	private final UUID key;

	public TastingDetailsRequest(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
}
