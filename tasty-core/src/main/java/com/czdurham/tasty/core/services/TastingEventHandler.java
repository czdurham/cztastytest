package com.czdurham.tasty.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.czdurham.tasty.core.domain.taster.Taster;
import com.czdurham.tasty.core.domain.tasting.Tasting;
import com.czdurham.tasty.core.domain.tasting.TastingDetails;
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
import com.czdurham.tasty.core.repository.TasterRepository;
import com.czdurham.tasty.core.repository.TastingRepository;

@Service
public class TastingEventHandler implements TastingService {
	private final TastingRepository tastingRepository;
	private final TasterRepository tasterRepository;

	public TastingEventHandler(TastingRepository tastingRepository, TasterRepository tasterRepository) {
		this.tastingRepository = tastingRepository;
		this.tasterRepository = tasterRepository;
	}

	@Override
	public TastingCreatedEvent requestTastingCreate(TastingCreateRequest request) {
		Tasting tasting = Tasting.fromDetails(request.getTastingDetails());
		tasting = tastingRepository.save(tasting);

		return new TastingCreatedEvent(tasting.getKey(), tasting.toDetails());
	}

	@Override
	public TastingDetailsEvent requestTastingDetails(TastingDetailsRequest request) {
		Tasting tasting = tastingRepository.findById(request.getKey());
		if (tasting == null) {
			return TastingDetailsEvent.notFound(request.getKey());
		}

		return new TastingDetailsEvent(request.getKey(), tasting.toDetails());
	}

	@Override
	public TastingsDetailsEvent requestTastingsDetails(TastingsDetailsRequest request) {
		List<Tasting> tastings = tastingRepository.findAll();
		List<TastingDetails> tastingDetails = new ArrayList<>();
		int page = request.pageIsSet() ? request.getPage() - 1 : 0;
		int size = request.sizeIsSet() ? request.getSize() : 10;
		int start = page * size;
		start = start < tastings.size() ? start : tastings.size();
		int end = start + size;
		end = end < tastings.size() ? end : tastings.size();
		for(Tasting tasting : tastings.subList(start, end)) {
			tastingDetails.add(tasting.toDetails());
		}

		return new TastingsDetailsEvent(page, size, tastings.size(), tastingDetails);
	}

	@Override
	public TastingStatusEvent requestTastingStatus(TastingStatusRequest request) {
		Tasting tasting = tastingRepository.findById(request.getKey());
		if (tasting == null) {
			return TastingStatusEvent.notFound(request.getKey());
		}

		return new TastingStatusEvent(request.getKey(), tasting.getStatusDetails());
	}

	@Override
	public TastingDeletedEvent requestTastingDelete(TastingDeleteRequest request) {
		Tasting tasting = tastingRepository.findById(request.getKey());
		if (tasting == null) {
			return TastingDeletedEvent.notFound(request.getKey());
		}

		TastingDetails tastingDetails = tasting.toDetails();
		if(!tasting.canBeDeleted()) {
			return TastingDeletedEvent.forbidden(request.getKey(), tastingDetails);
		}

		tastingRepository.delete(request.getKey());
		return new TastingDeletedEvent(request.getKey(), tastingDetails);
	}

	@Override
	public TastingTasterCreatedEvent requestTasterCreate(TastingTasterCreateRequest request) {
		Taster taster = Taster.fromDetails(request.getTasterDetails());
		taster = tasterRepository.save(taster);

		return new TastingTasterCreatedEvent(taster.getKey(), taster.toDetails());
	}
}
