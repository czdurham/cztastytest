package com.czdurham.tasty.core.repository;

import java.util.List;

public interface GenericRepository<KeyType, EntityType> {
	EntityType save(EntityType entity);
	void delete(EntityType key);
	EntityType findById(KeyType key);
	List<EntityType> findAll();
}
