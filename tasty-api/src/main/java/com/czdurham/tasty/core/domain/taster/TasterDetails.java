package com.czdurham.tasty.core.domain.taster;

import java.util.UUID;

public class TasterDetails {
	private UUID key;

	public TasterDetails() {}

	public TasterDetails(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}

	protected void setKey(UUID key) {
		this.key = key;
	}
}
