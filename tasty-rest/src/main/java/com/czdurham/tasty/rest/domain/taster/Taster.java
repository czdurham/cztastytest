package com.czdurham.tasty.rest.domain.taster;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement
public class Taster extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 6217775757092150138L;
	private UUID key;

	public UUID getKey() {
		return key;
	}

	public void setKey(UUID key) {
		this.key = key;
	}
}
