package com.czdurham.tasty.core.services;

import com.czdurham.tasty.core.events.tasting.TastingCreateRequest;
import com.czdurham.tasty.core.events.tasting.TastingCreatedEvent;
import com.czdurham.tasty.core.events.tasting.TastingDeleteRequest;
import com.czdurham.tasty.core.events.tasting.TastingDeletedEvent;
import com.czdurham.tasty.core.events.tasting.TastingDetailsEvent;
import com.czdurham.tasty.core.events.tasting.TastingDetailsRequest;
import com.czdurham.tasty.core.events.tasting.TastingStatusEvent;
import com.czdurham.tasty.core.events.tasting.TastingStatusRequest;
import com.czdurham.tasty.core.events.tasting.TastingTasterCreateRequest;
import com.czdurham.tasty.core.events.tasting.TastingTasterCreatedEvent;
import com.czdurham.tasty.core.events.tasting.TastingsDetailsEvent;
import com.czdurham.tasty.core.events.tasting.TastingsDetailsRequest;

public interface TastingService {
	public TastingCreatedEvent requestTastingCreate(TastingCreateRequest request);
	public TastingDetailsEvent requestTastingDetails(TastingDetailsRequest request);
	public TastingsDetailsEvent requestTastingsDetails(TastingsDetailsRequest request);
	public TastingStatusEvent requestTastingStatus(TastingStatusRequest request);
	public TastingDeletedEvent requestTastingDelete(TastingDeleteRequest request);

	public TastingTasterCreatedEvent requestTasterCreate(TastingTasterCreateRequest request);
}