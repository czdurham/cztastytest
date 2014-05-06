package com.czdurham.tasty.core.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.czdurham.tasty.core.domain.taster.Taster;

public class TasterMemoryRepository implements TasterRepository {
	private Map<UUID, Taster> tasters;

	public TasterMemoryRepository(final Map<UUID, Taster> tasters) {
		this.tasters = tasters;
	}

	@Override
	public synchronized Taster save(Taster taster) {
		Map<UUID, Taster> tasters = new HashMap<>(this.tasters);
		tasters.put(taster.getKey(), taster);
		this.tasters = Collections.unmodifiableMap(tasters);

		return taster;
	}

	@Override
	public synchronized void delete(UUID key) {
		if(tasters.containsKey(key)) {
			Map<UUID, Taster> tasters = new HashMap<>(this.tasters);
			tasters.remove(key);
			this.tasters = Collections.unmodifiableMap(tasters);
		}
	}

	@Override
	public Taster findById(UUID key) {
		return tasters.get(key);
	}

	@Override
	public List<Taster> findAll() {
		return Collections.unmodifiableList(new ArrayList<Taster>(tasters.values()));
	}
}
