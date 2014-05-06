package com.czdurham.tasty.core.events.taster;

import java.util.List;

import com.czdurham.tasty.core.domain.taster.TasterDetails;
import com.czdurham.tasty.core.events.PaginatedReadEvent;

public class TastersDetailsEvent extends PaginatedReadEvent {
	private final List<TasterDetails> tasterDetails;

	public TastersDetailsEvent(int page, int size, int total, List<TasterDetails> tasterDetails) {
		super(page, size, total);
		this.tasterDetails = tasterDetails;
	}

	public List<TasterDetails> getTasterDetails() {
		return tasterDetails;
	}
}
