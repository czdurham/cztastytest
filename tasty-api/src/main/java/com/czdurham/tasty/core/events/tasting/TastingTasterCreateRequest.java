package com.czdurham.tasty.core.events.tasting;

import java.util.UUID;

import com.czdurham.tasty.core.domain.taster.TasterDetails;
import com.czdurham.tasty.core.events.CreateRequest;

public class TastingTasterCreateRequest extends CreateRequest {
	private final UUID tastingKey;
	private final TasterDetails tasterDetails;

	public TastingTasterCreateRequest(UUID tastingKey, TasterDetails tasterDetails) {
		this.tastingKey = tastingKey;
		this.tasterDetails = tasterDetails;
	}

	public UUID getTastingKey() {
		return tastingKey;
	}

	public TasterDetails getTasterDetails() {
		return tasterDetails;
	}

}
