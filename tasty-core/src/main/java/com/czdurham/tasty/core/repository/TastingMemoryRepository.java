package com.czdurham.tasty.core.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.Tasting;

public class TastingMemoryRepository implements TastingRepository {
	private Map<UUID, Tasting> tastings;

	public TastingMemoryRepository(final Map<UUID, Tasting> tastings) {
		this.tastings = tastings;
	}

	@Override
	public synchronized Tasting save(Tasting tasting) {
		Map<UUID, Tasting> tastings = new HashMap<>(this.tastings);
		tastings.put(tasting.getKey(), tasting);
		this.tastings = Collections.unmodifiableMap(tastings);

		return tasting;
	}

	@Override
	public synchronized void delete(UUID key) {
		if(tastings.containsKey(key)) {
			Map<UUID, Tasting> tastings = new HashMap<>(this.tastings);
			tastings.remove(key);
			this.tastings = Collections.unmodifiableMap(tastings);
		}
	}

	@Override
	public Tasting findById(UUID key) {
		return tastings.get(key);
	}

	@Override
	public List<Tasting> findAll() {
		return Collections.unmodifiableList(new ArrayList<Tasting>(tastings.values()));
	}
}
