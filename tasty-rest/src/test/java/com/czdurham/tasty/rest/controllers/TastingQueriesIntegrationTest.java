package com.czdurham.tasty.rest.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.czdurham.tasty.core.events.tasting.TastingDetailsEvent;
import com.czdurham.tasty.core.events.tasting.TastingDetailsRequest;
import com.czdurham.tasty.core.services.TastingService;
import com.czdurham.tasty.rest.events.tasting.TastingEventsFixtures;

public class TastingQueriesIntegrationTest {
	MockMvc mockMvc;

	@InjectMocks
	TastingQueriesController controller;

	@Mock
	TastingService service;

	UUID key = UUID.randomUUID();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
			.setMessageConverters(new MappingJackson2HttpMessageConverter(),
					new Jaxb2RootElementHttpMessageConverter()).build();
	}

	@Test
	public void thatViewOrderUsesHttpNotFound() throws Exception {
		Mockito.when(service.requestTastingDetails(Mockito.any(TastingDetailsRequest.class)))
			.thenReturn(TastingEventsFixtures.tastingDetailsNotFound(key));

		mockMvc.perform(
				MockMvcRequestBuilders.get("/tastings/{id}",  key)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void thatViewOrderUsesHttpOK() throws Exception {
		Mockito.when(service.requestTastingDetails(Mockito.any(TastingDetailsRequest.class)))
			.thenReturn(TastingEventsFixtures.tastingDetails(key));

		mockMvc.perform(
				MockMvcRequestBuilders.get("/tastings/{id}", key)
						.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void thatViewTastingRendersJsonCorrectly() throws Exception {
		TastingDetailsEvent event = TastingEventsFixtures.tastingDetails(key);
		Mockito.when(service.requestTastingDetails(Mockito.any(TastingDetailsRequest.class)))
			.thenReturn(event);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/tastings/{id}", key)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
		            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(event.getTastingDetails().getName()))
					.andExpect(MockMvcResultMatchers.jsonPath("$.key").value(key.toString()));
	}

	@Test
	public void thatViewTastingRendersXmlCorrectly() throws Exception {
		TastingDetailsEvent event = TastingEventsFixtures.tastingDetails(key);
		Mockito.when(service.requestTastingDetails(Mockito.any(TastingDetailsRequest.class)))
			.thenReturn(event);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/tastings/{id}", key)
						.accept(MediaType.TEXT_XML))
					.andDo(MockMvcResultHandlers.print())
		            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_XML))
					.andExpect(MockMvcResultMatchers.xpath("/tasting/name").string(event.getTastingDetails().getName()))
					.andExpect(MockMvcResultMatchers.xpath("/tasting/key").string(key.toString()));
	}
}
