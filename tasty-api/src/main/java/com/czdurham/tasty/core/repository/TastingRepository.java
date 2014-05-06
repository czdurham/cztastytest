package com.czdurham.tasty.core.repository;

import java.util.List;
import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.Tasting;

public interface TastingRepository {
	Tasting save(Tasting tasting);
	void delete(UUID key);
	Tasting findById(UUID key);
	List<Tasting> findAll();
}
