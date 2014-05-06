package com.czdurham.hello;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
	private static final String TEMPLATE = "Hello, %s";
	
	@RequestMapping("/greeting")
	@ResponseBody
	public HttpEntity<Greeting> handleGreeting(
			@RequestParam(value="name",defaultValue="World") String name) {
		Greeting greeting = new Greeting(String.format(TEMPLATE, name));
		greeting.add(linkTo(methodOn(GreetingController.class).handleGreeting(name)).withSelfRel());
		
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}
}
