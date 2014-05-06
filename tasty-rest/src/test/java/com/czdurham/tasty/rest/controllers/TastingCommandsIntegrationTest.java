package com.czdurham.tasty.rest.controllers;

import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.czdurham.tasty.core.events.tasting.TastingCreateRequest;
import com.czdurham.tasty.core.events.tasting.TastingDeleteRequest;
import com.czdurham.tasty.core.services.TastingService;
import com.czdurham.tasty.rest.domain.tasting.TastingDomainFixtures;
import com.czdurham.tasty.rest.events.tasting.TastingEventsFixtures;

public class TastingCommandsIntegrationTest {
	MockMvc mockMvc;

	@InjectMocks
	TastingCommandsController controller;

	@Mock
	TastingService service;

	UUID key = UUID.fromString("628d5237-c64d-4253-b06e-1142b72832c5");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
			.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

		Mockito.when(service.requestTastingCreate(Mockito.any(TastingCreateRequest.class)))
			.thenReturn(TastingEventsFixtures.tastingCreated(key));
	}

	@Test
	public void thatCreateOrderUsesHttpCreated() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/tastings")
						.content(TastingDomainFixtures.simpleTastingJSON(key))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void thatCreateOrderRendersAsJson() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/tastings")
						.content(TastingDomainFixtures.simpleTastingJSON(key))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Northern Brewery"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.key").value(key.toString()));
	}

	@Test
	public void thatCreateOrderPassesLocationHeader() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/tastings")
						.content(TastingDomainFixtures.simpleTastingJSON(key))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.header().string("Location", Matchers.endsWith("/tastings/" + key)));
	}

	@Test
	public void thatDeleteOrderUsesHttpOkOnSuccess() throws Exception {
		Mockito.when(service.requestTastingDelete(Mockito.any(TastingDeleteRequest.class)))
			.thenReturn(TastingEventsFixtures.tastingDeleted(key));

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/tastings/{id}", key)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk());

		ArgumentCaptor<TastingDeleteRequest> argument = ArgumentCaptor.forClass(TastingDeleteRequest.class);
		Mockito.verify(service).requestTastingDelete(argument.capture());
		Assert.assertEquals(key, argument.getValue().getKey());
	}

	@Test
	public void thatDeleteOrderUsesHttpNotFoundOnEntityLookupFailure() throws Exception {
		Mockito.when(service.requestTastingDelete(Mockito.any(TastingDeleteRequest.class)))
			.thenReturn(TastingEventsFixtures.tastingDeletedNotFound(key));

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/tastings/{id}", key)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void thatDeleteOrderUsesHttpForbiddenOnEntityDeletionFailure() throws Exception {
		Mockito.when(service.requestTastingDelete(Mockito.any(TastingDeleteRequest.class)))
			.thenReturn(TastingEventsFixtures.tastingDeletedFailed(key));

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/tastings/{id}", key)
						.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isForbidden());
	}
}
