package com.czdurham.tasty.core.events.taster;

import java.util.UUID;

import com.czdurham.tasty.core.events.ReadRequest;

public class TasterDetailsRequest extends ReadRequest {
	private final UUID key;

	public TasterDetailsRequest(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
}
