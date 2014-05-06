package com.czdurham.tasty.core.domain;

import java.util.Date;

public class TimestampedEnum<E extends Enum<?>> {
	private Date date;
	private E enumValue;
	
	public TimestampedEnum(Date date, E status) {
		this.date = date;
		this.enumValue = status;
	}
	
	public Date getDate() {
		return date;
	}
	
	public E getEnumValue() {
		return enumValue;
	}
}
