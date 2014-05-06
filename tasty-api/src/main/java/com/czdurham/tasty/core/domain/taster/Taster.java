package com.czdurham.tasty.core.domain.taster;

import java.util.UUID;

import org.springframework.beans.BeanUtils;

public class Taster {
	private final UUID key;

	public Taster() {
		key = UUID.randomUUID();
	}

	public UUID getKey() {
		return key;
	}

	public TasterDetails toDetails() {
		TasterDetails details = new TasterDetails(getKey());
		BeanUtils.copyProperties(this, details);
		return details;
	}

	public static Taster fromDetails(TasterDetails details) {
		Taster taster = new Taster();
	    BeanUtils.copyProperties(details, taster);
		return taster;
	}
}
