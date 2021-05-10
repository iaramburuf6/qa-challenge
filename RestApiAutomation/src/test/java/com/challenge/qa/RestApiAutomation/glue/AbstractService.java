package com.challenge.qa.RestApiAutomation.glue;

import java.io.IOException;
import java.util.Properties;

import com.challenge.qa.RestApiAutomation.data.SharedData;
import com.challenge.qa.RestApiAutomation.properties.PropertiesByProfile;

import io.cucumber.java.en.Given;

public class AbstractService {
	
	private SharedData sharedData;
    
    public AbstractService(SharedData sharedData) {
		this.sharedData = sharedData;
	}

	@Given("user load api data")
    public void user_load_api_data() {
    	try {
            Properties properties = new PropertiesByProfile("application");
            String targetUrl = properties.getProperty("target-url");
            sharedData.setTargetUrl(targetUrl);
        } catch (IOException exception) {
            throw new RuntimeException("Selenium driver configuration file not found");
        }
    }
}
