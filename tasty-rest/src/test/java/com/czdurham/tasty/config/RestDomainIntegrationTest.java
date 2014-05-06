package com.czdurham.tasty.config;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.czdurham.tasty.rest.domain.tasting.TastingDomainFixtures;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CoreConfig.class, MvcConfig.class})
public class RestDomainIntegrationTest {
	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	UUID key = UUID.fromString("628d5237-c64d-4253-b06e-1142b72832c5");

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addANewOrderToTheSystem() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/tastings")
	                    .content(TastingDomainFixtures.simpleTastingJSON(key))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		            .andDo(MockMvcResultHandlers.print())
		            .andExpect(MockMvcResultMatchers.status().isCreated());

		mockMvc.perform(
				MockMvcRequestBuilders.get("/tastings")
                    	.accept(MediaType.APPLICATION_JSON))
		            .andDo(MockMvcResultHandlers.print())
		            .andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Northern Brewery"));
	}
}
