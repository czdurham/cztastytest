package com.czdurham.tasty.core.events.tasting;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.CreateRequest;

public class TastingCreateRequest extends CreateRequest {
	private final TastingDetails tastingDetails;

	public TastingCreateRequest(TastingDetails tastingDetails) {
		this.tastingDetails = tastingDetails;
	}

	public TastingDetails getTastingDetails() {
		return tastingDetails;
	}
}
