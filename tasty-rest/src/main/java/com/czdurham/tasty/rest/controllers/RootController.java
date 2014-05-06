package com.czdurham.tasty.rest.controllers;

import static com.czdurham.tasty.rest.config.Constants.DEFAULT_PAGE;
import static com.czdurham.tasty.rest.config.Constants.DEFAULT_SIZE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/", produces="application/json")
public class RootController {
	private static final Class<RootController> THIS_CLASS = RootController.class;
	private static final Logger LOG = LoggerFactory.getLogger(THIS_CLASS);

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResourceSupport handleRoot() {
		LOG.debug("Entered handleRoot");
		ResourceSupport response = new ResourceSupport();

		response.add(linkTo(methodOn(THIS_CLASS).handleRoot()).withSelfRel());
		response.add(linkTo(methodOn(TastingQueriesController.class).viewTastings(DEFAULT_PAGE, DEFAULT_SIZE)).withRel("tastings"));

		return response;
	}
}