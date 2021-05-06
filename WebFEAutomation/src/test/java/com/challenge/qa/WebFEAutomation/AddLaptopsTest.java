package com.challenge.qa.WebFEAutomation;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(publish = true,features = {"src/test/resources/features"}, glue = {"com.challenge.qa.WebFEAutomation.glue"},
        		plugin = { "de.monochromata.cucumber.report.PrettyReports:target/cucumber-reports" },
        tags =  "@Laptop and @Add", monochrome = true)
public class AddLaptopsTest {

}
