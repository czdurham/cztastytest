package com.czdurham.tasty.core.domain.product;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

public class Product {
	private final UUID key;
	private String name;
	private List<UUID> makers;
	private List<String> makerNames;
//	private final List<UUID> changeHistory; ? TODO Do we want to track changes?

	public Product() {
		key = UUID.randomUUID();
	}

	public boolean canBeDeleted() {
		return true;
	}

	public UUID getKey() {
		return key;
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

	public ProductDetails toDetails() {
		ProductDetails details = new ProductDetails(getKey());
		BeanUtils.copyProperties(this, details);
		return details;
	}

	public static Product fromDetails(ProductDetails details) {
		Product product = new Product();
	    BeanUtils.copyProperties(details, product);
		return product;
	}
}