package com.czdurham.tasty.rest.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.czdurham.tasty.core.domain.taster.Taster;
import com.czdurham.tasty.core.domain.taster.TasterDetails;
import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.tasting.TastingDeleteRequest;
import com.czdurham.tasty.core.events.tasting.TastingDeletedEvent;
import com.czdurham.tasty.core.events.tasting.TastingTasterCreateRequest;
import com.czdurham.tasty.core.events.tasting.TastingTasterCreatedEvent;
import com.czdurham.tasty.core.services.TastingService;
import com.czdurham.tasty.rest.domain.tasting.Tasting;

@Controller
@RequestMapping(value="/tastings/{tastingId}")
public class TastingTasterCommandsController {
	private static final Class<TastingTasterCommandsController> THIS_CLASS = TastingTasterCommandsController.class;
	private static final Logger LOG = LoggerFactory.getLogger(THIS_CLASS);

	@Autowired
	private TastingService tastingService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Taster> addTastingTaster(
			@PathVariable UUID tastingId,
			@RequestBody Taster taster,
			UriComponentsBuilder builder) {
		LOG.debug("Entered addTastingTaster");
		TasterDetails tasterDetails = taster.toDetails();
		TastingTasterCreateRequest request = new TastingTasterCreateRequest(tastingId, tasterDetails);
		TastingTasterCreatedEvent event = tastingService.requestTasterCreate(request);

		TasterDetails newTasterDetails = event.getTasterDetails();
		Taster newTaster = Taster.fromDetails(newTasterDetails);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/tastings/{tastingId}/{id}").buildAndExpand(tastingId, newTasterDetails.getKey()).toUri());

		return new ResponseEntity<Taster>(newTaster, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Tasting> deleteTastingTaster(
			@PathVariable UUID tastingId,
			@PathVariable UUID id) {
		LOG.debug("Entered deleteTasting");
		TastingDeleteRequest request = new TastingDeleteRequest(id);
		TastingDeletedEvent event = tastingService.requestTastingDelete(request);

		if (!event.isFound()) {
			return new ResponseEntity<Tasting>(HttpStatus.NOT_FOUND);
		}

		TastingDetails tastingDetails = event.getTastingDetails();
		Tasting tasting = Tasting.fromDetails(tastingDetails);

		if (!event.isDeleted()) {
			return new ResponseEntity<Tasting>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<Tasting>(tasting, HttpStatus.OK);
	}
}