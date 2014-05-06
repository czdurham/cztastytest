package com.czdurham.tasty.core.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.czdurham.tasty.core.domain.EntityBase;

public class AbstractGenericMemoryRepository<KeyType, EntityType extends EntityBase<KeyType>> implements
		GenericRepository<KeyType, EntityType> {
	private Map<KeyType, EntityType> entities;

	public AbstractGenericMemoryRepository(final Map<KeyType, EntityType> entities) {
		this.entities = entities;
	}

	@Override
	public synchronized EntityType save(EntityType entity) {
		Map<KeyType, EntityType> entities = new HashMap<>(this.entities);
		entities.put(entity.getKey(), entity);
		this.entities = Collections.unmodifiableMap(entities);

		return entity;
	}

	@Override
	public void delete(EntityType key) {
		if(entities.containsKey(key)) {
			Map<KeyType, EntityType> entities = new HashMap<>(this.entities);
			entities.remove(key);
			this.entities = Collections.unmodifiableMap(entities);
		}
	}

	@Override
	public EntityType findById(KeyType key) {
		return entities.get(key);
	}

	@Override
	public List<EntityType> findAll() {
		return Collections.unmodifiableList(new ArrayList<EntityType>(entities.values()));
	}

}
