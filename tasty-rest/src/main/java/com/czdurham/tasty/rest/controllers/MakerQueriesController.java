package com.czdurham.tasty.rest.controllers;

import static com.czdurham.tasty.rest.config.Constants.DEFAULT_PAGE;
import static com.czdurham.tasty.rest.config.Constants.DEFAULT_SIZE;
import static com.czdurham.tasty.rest.config.Constants.DEFAULT_STRING_PAGE;
import static com.czdurham.tasty.rest.config.Constants.DEFAULT_STRING_SIZE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/makers", produces="application/json")
public class MakerQueriesController {
	private static final Class<MakerQueriesController> THIS_CLASS = MakerQueriesController.class;
	private static final Logger LOG = LoggerFactory.getLogger(THIS_CLASS);

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResourceSupport handleMakers(
			@RequestParam(defaultValue=DEFAULT_STRING_PAGE) int page,
			@RequestParam(defaultValue=DEFAULT_STRING_SIZE) int size) {
		LOG.debug("Entered handleMakers");
		ResourceSupport response = new ResourceSupport();

		response.add(linkTo(methodOn(THIS_CLASS).handleMakers(DEFAULT_PAGE, DEFAULT_SIZE)).withSelfRel());

		return response;
	}
}
