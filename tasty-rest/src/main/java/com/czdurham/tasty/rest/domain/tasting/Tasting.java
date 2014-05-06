package com.czdurham.tasty.rest.domain.tasting;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.rest.controllers.TastingQueriesController;

@XmlRootElement
public class Tasting extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 2506886299803624406L;
	private UUID key;
	private String name;
	private List<String> tasters;

	public UUID getKey() {
		return key;
	}

	public void setKey(UUID key) {
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

	public TastingDetails toDetails() {
		TastingDetails details = new TastingDetails(getKey());

		BeanUtils.copyProperties(this, details);
//		details.setName(getName());
//		details.setTasters(getTasters());

		return details;
	}

	public static Tasting fromDetails(TastingDetails details) {
		Tasting tasting = new Tasting();

	    BeanUtils.copyProperties(details, tasting);
//		tasting.setKey(details.getKey());
//		tasting.setName(details.getName());
//		tasting.setTasters(details.getTasters());
		tasting.add(ControllerLinkBuilder.linkTo(TastingQueriesController.class).slash(tasting.key).withSelfRel());
		tasting.add(ControllerLinkBuilder.linkTo(TastingQueriesController.class).slash(tasting.key).slash("status").withRel("Tasting Status"));

		return tasting;
	}
}
