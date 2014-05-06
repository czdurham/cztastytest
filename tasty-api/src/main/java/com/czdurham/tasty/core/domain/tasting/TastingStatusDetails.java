package com.czdurham.tasty.core.domain.tasting;

import java.util.Date;

import com.czdurham.tasty.core.domain.TimestampedEnum;

public class TastingStatusDetails extends TimestampedEnum<TastingStatus> {
	public TastingStatusDetails(Date date, TastingStatus status) {
		super(date, status);
	}
}
