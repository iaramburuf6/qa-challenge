package com.challenge.qa.RestApiAutomation.model.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetStatus {

	AVAILABLE("available"),
	PENDING("pending"),
	SOLD("sold");
	
	private final String value;

	private PetStatus(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
