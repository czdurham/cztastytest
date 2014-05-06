package com.czdurham.tasty.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/tastings/{tastingId}")
public class TastingTasterQueryController {
	private static final Class<TastingTasterQueryController> THIS_CLASS = TastingTasterQueryController.class;
	private static final Logger LOG = LoggerFactory.getLogger(THIS_CLASS);

}
