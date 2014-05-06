package com.czdurham.tasty.core.domain.tasting;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TastingDetails {
	private UUID key;
	private String name;
	private List<String> tasters;

	public TastingDetails() {}

	public TastingDetails(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}

	protected void setKey(UUID key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTasters() {
		return tasters;
	}

	public void setTasters(List<String> tasters) {
		if(null == tasters) {
			this.tasters = Collections.emptyList();
		} else {
			this.tasters = Collections.unmodifiableList(tasters);
		}
	}
}
