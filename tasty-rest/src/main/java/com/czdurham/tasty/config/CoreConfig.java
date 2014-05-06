package com.czdurham.tasty.config;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.czdurham.tasty.core.domain.taster.Taster;
import com.czdurham.tasty.core.domain.tasting.Tasting;
import com.czdurham.tasty.core.repository.TasterMemoryRepository;
import com.czdurham.tasty.core.repository.TasterRepository;
import com.czdurham.tasty.core.repository.TastingMemoryRepository;
import com.czdurham.tasty.core.repository.TastingRepository;
import com.czdurham.tasty.core.services.TastingEventHandler;
import com.czdurham.tasty.core.services.TastingService;

@Configuration
//@ComponentScan(basePackages={"com.czdurham.tasty.core"})
public class CoreConfig {
	@Bean
	public TastingService createTastingService(
			TastingRepository tastingRepository,
			TasterRepository tasterRepository) {
		return new TastingEventHandler(tastingRepository, tasterRepository);
	}

	@Bean
	public TastingRepository createTastingRepository() {
		return new TastingMemoryRepository(new HashMap<UUID, Tasting>());
	}

	@Bean
	public TasterRepository createTasterRepository() {
		return new TasterMemoryRepository(new HashMap<UUID, Taster>());
	}
}