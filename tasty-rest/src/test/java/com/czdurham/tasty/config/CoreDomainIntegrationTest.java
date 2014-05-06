package com.czdurham.tasty.config;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.events.tasting.TastingCreateRequest;
import com.czdurham.tasty.core.events.tasting.TastingsDetailsEvent;
import com.czdurham.tasty.core.events.tasting.TastingsDetailsRequest;
import com.czdurham.tasty.core.services.TastingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class})
public class CoreDomainIntegrationTest {
	@Autowired
	TastingService tastingService;

	@Test
	public void addNewTastingToTheSystem() {
		TastingCreateRequest request = new TastingCreateRequest(new TastingDetails());
		tastingService.requestTastingCreate(request);

		TastingsDetailsEvent event = tastingService.requestTastingsDetails(new TastingsDetailsRequest());
		TestCase.assertEquals(1, event.getTotal());
	}
}
