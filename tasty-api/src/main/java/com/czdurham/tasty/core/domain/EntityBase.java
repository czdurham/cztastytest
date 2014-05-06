package com.czdurham.tasty.core.domain;


public class EntityBase<KeyType> {
	private KeyType key;

	public EntityBase(KeyType key) {
		setKey(key);
	}

	public KeyType getKey() {
		return key;
	}

	public void setKey(KeyType key) {
		this.key = key;
	}
}
