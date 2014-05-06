package com.czdurham.tasty.rest.domain.tasting;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.czdurham.tasty.core.domain.tasting.TastingDetails;
import com.czdurham.tasty.core.domain.tasting.TastingStatus;
import com.czdurham.tasty.core.domain.tasting.TastingStatusDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TastingDomainFixtures {
//	private static Random random = new Random(10181977);
	private static String[] adjectives = {"Great", "Northern", "Western", "Classic", "Rock", "Green"};
	private static String[] noun = {"Brewery", "Beer Company", "Suds Maker"};

	public static TastingStatusDetails createTastingStatusDetails(TastingStatus status) {
		return new TastingStatusDetails(new Date(), status);
	}

	public static TastingDetails createTastingDetails(UUID key) {
		TastingDetails tastingDetails = new TastingDetails(key);
		String name = nextRandomName(key.hashCode());
		tastingDetails.setName(name);
		return tastingDetails;
	}

	public static Tasting createTasting(UUID key) {
		Tasting tasting = new Tasting();
		tasting.setKey(key);
		String name = nextRandomName(key.hashCode());
		tasting .setName(name);
		return tasting;
	}

	public static String nextRandomName(int seed) {
		Random random = new Random(seed);
		StringBuilder sb = new StringBuilder();
		int adjCount = 1 + random.nextInt(1);
		for(int i = 0; i < adjCount; ++ i) {
			sb.append(adjectives[random.nextInt(adjectives.length)]);
			sb.append(' ');
		}
		sb.append(noun[random.nextInt(noun.length)]);
		return sb.toString();
	}

	public static String simpleTastingJSON() {
		return simpleTastingJSON(UUID.fromString("2c31b425-77f8-4b1c-acaf-fff5330b04f5"));
	}

	public static String simpleTastingJSON(UUID key) {
		ObjectMapper mapper = new ObjectMapper();
		TastingDetails tasting = createTastingDetails(key);
		try {
			String json = mapper.writeValueAsString(tasting);
			return json;
		} catch (JsonProcessingException e) {
			return "{EXCEPTION}";
		}
	}
}
