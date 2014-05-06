package com.czdurham.tasty.core.domain.product;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProductDetails {
	private UUID key;
	private String name;
	private List<UUID> makers;
	private List<String> makerNames;

	public ProductDetails() {}

	public ProductDetails(UUID key) {
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

	public List<UUID> getMakers() {
		return makers;
	}

	public void setMakers(List<UUID> makers) {
		if(null == makers) {
			this.makers = Collections.emptyList();
		} else {
			this.makers = Collections.unmodifiableList(makers);
		}
	}

	public List<String> getMakerNamess() {
		return makerNames;
	}

	public void setMakerNames(List<String> makerNames) {
		if(null == makerNames) {
			this.makerNames = Collections.emptyList();
		} else {
			this.makerNames = Collections.unmodifiableList(makerNames);
		}
	}
}
