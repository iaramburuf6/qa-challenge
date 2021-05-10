package com.challenge.qa.RestApiAutomation.data;

import com.challenge.qa.RestApiAutomation.model.entity.Pet;

public class SharedData {

    private String targetUrl;
    
    private Pet pet;
    
    private int responseStatusCode;

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	public void setResponseStatusCode(int responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}
}

