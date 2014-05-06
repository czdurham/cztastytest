package com.czdurham.tasty.core.events.tasting;

import java.util.List;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.PaginatedReadEvent;

public class TastingsDetailsEvent extends PaginatedReadEvent {
	private final List<TastingDetails> tastingDetails;

	public TastingsDetailsEvent(int page, int size, int total, List<TastingDetails> tastingDetails) {
		super(page, size, total);
		this.tastingDetails = tastingDetails;
	}

	public List<TastingDetails> getTastingDetails() {
		return tastingDetails;
	}
}
