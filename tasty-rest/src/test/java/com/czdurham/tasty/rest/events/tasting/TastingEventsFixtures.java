package com.czdurham.tasty.rest.events.tasting;

import static com.czdurham.tasty.rest.domain.tasting.TastingDomainFixtures.createTastingDetails;
import static com.czdurham.tasty.rest.domain.tasting.TastingDomainFixtures.createTastingStatusDetails;

import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.TastingStatus;
import com.czdurham.tasty.core.events.tasting.TastingCreatedEvent;
import com.czdurham.tasty.core.events.tasting.TastingDeletedEvent;
import com.czdurham.tasty.core.events.tasting.TastingDetailsEvent;
import com.czdurham.tasty.core.events.tasting.TastingStatusEvent;

public class TastingEventsFixtures {
	public static TastingStatusEvent tastingStatusNotFount(UUID key) {
		return TastingStatusEvent.notFound(key);
	}
	public static TastingStatusEvent tastingStatus(UUID key, TastingStatus tastingStatus) {
		return new TastingStatusEvent(key, createTastingStatusDetails(tastingStatus));
	}
	public static TastingDetailsEvent tastingDetailsNotFound(UUID key) {
		return TastingDetailsEvent.notFound(key);
	}
	public static TastingDetailsEvent tastingDetails(UUID key) {
		return new TastingDetailsEvent(key, createTastingDetails(key));
	}
	public static TastingCreatedEvent tastingCreated(UUID key) {
		return new TastingCreatedEvent(key, createTastingDetails(key));
	}
	public static TastingDeletedEvent tastingDeleted(UUID key) {
		return new TastingDeletedEvent(key, createTastingDetails(key));
	}
	public static TastingDeletedEvent tastingDeletedFailed(UUID key) {
		return TastingDeletedEvent.forbidden(key, createTastingDetails(key));
	}
	public static TastingDeletedEvent tastingDeletedNotFound(UUID key) {
		return TastingDeletedEvent.notFound(key);
	}
}