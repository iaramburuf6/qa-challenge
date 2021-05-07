package com.challenge.qa.WebFEAutomation;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.challenge.qa.WebFEAutomation.driver.SeleniumDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(publish = true,features = {"src/test/resources/features"}, glue = {"com.challenge.qa.WebFEAutomation.glue"},
        		plugin = { "de.monochromata.cucumber.report.PrettyReports:target/cucumber-reports" },
        tags =  "@Laptop and @Add", monochrome = true)
public class AddLaptopsTest {
	
	@AfterClass
    public static void teardown() {
        SeleniumDriver.close();
	}

}
