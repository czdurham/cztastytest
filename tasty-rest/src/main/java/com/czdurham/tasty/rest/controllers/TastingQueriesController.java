package com.czdurham.tasty.rest.controllers;

import static com.czdurham.tasty.rest.config.Constants.DEFAULT_STRING_PAGE;
import static com.czdurham.tasty.rest.config.Constants.DEFAULT_STRING_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.tasting.TastingDetailsEvent;
import com.czdurham.tasty.core.events.tasting.TastingDetailsRequest;
import com.czdurham.tasty.core.events.tasting.TastingsDetailsEvent;
import com.czdurham.tasty.core.events.tasting.TastingsDetailsRequest;
import com.czdurham.tasty.core.services.TastingService;
import com.czdurham.tasty.rest.domain.tasting.Tasting;

@Controller
@RequestMapping(value="/tastings")
public class TastingQueriesController {
	private static final Class<TastingQueriesController> THIS_CLASS = TastingQueriesController.class;
	private static final Logger LOG = LoggerFactory.getLogger(THIS_CLASS);

	@Autowired
	private TastingService tastingService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Tasting> viewTastings(
			@RequestParam(defaultValue=DEFAULT_STRING_PAGE) int page,
			@RequestParam(defaultValue=DEFAULT_STRING_SIZE) int size) {
		LOG.debug("Entered viewTastings");

		TastingsDetailsRequest request = new TastingsDetailsRequest();
		request.setPage(page);
		request.setSize(size);
		TastingsDetailsEvent event = tastingService.requestTastingsDetails(request);

		List<Tasting> tastings = new ArrayList<>();
		for(TastingDetails details : event.getTastingDetails()) {
			tastings.add(Tasting.fromDetails(details));
		}

		return tastings;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Tasting> viewTasting(@PathVariable UUID id) {
		LOG.debug("Entered viewTasting");

		TastingDetailsRequest request = new TastingDetailsRequest(id);
		TastingDetailsEvent event = tastingService.requestTastingDetails(request);

		if (!event.isFound()) {
			return new ResponseEntity<Tasting>(HttpStatus.NOT_FOUND);
		}

		TastingDetails tastingDetails = event.getTastingDetails();
		Tasting tasting = Tasting.fromDetails(tastingDetails);

		return new ResponseEntity<Tasting>(tasting, HttpStatus.OK);
	}
}
