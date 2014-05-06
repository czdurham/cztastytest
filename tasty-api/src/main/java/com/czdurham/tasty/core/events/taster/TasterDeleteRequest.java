package com.czdurham.tasty.core.events.taster;

import java.util.UUID;

import com.czdurham.tasty.core.events.DeleteRequest;

public class TasterDeleteRequest extends DeleteRequest {
	private final UUID key;

	public TasterDeleteRequest(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
}
