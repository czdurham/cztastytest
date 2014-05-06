package com.czdurham.tasty.core.repository;

import java.util.List;
import java.util.UUID;

import com.czdurham.tasty.core.domain.taster.Taster;

public interface UserRepository {
	Taster save(Taster taster);
	void delete(UUID key);
	Taster findById(UUID key);
	List<Taster> findAll();
}
