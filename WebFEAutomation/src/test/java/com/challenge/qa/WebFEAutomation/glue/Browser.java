package com.challenge.qa.WebFEAutomation.glue;

import com.challenge.qa.WebFEAutomation.driver.SeleniumDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Browser {
	
	@Given("user open the navigator")
	public void user_open_the_navigator() {
		SeleniumDriver.createDefaultDriver();
	}
	
	@Then("user access to the desired web page")
	public void user_access_to_the_desired_web_page() {
		SeleniumDriver.gotoTargetURL();
	}

	@Then("user close navigator")
	public void user_close_navigator() {
		SeleniumDriver.close();
	}
}
