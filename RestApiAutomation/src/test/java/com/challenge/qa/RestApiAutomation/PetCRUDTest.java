package com.challenge.qa.RestApiAutomation;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(publish = true,features = {"src/test/resources/features"}, glue = {"com.challenge.qa.RestApiAutomation.glue"},
        		plugin = { "de.monochromata.cucumber.report.PrettyReports:target/cucumber-reports" },
        tags =  "@CRUD", monochrome = true)
public class PetCRUDTest {

}
