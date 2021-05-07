package com.challenge.qa.WebFEAutomation;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.challenge.qa.WebFEAutomation.driver.SeleniumDriver;


@RunWith(Suite.class)
@Suite.SuiteClasses({ AddLaptopsTest.class, DeleteLaptopsOnCartTest.class, PurchaseTest.class})
public class BuyLaptopsTest {
	
	@AfterClass
    public static void teardown() {
        SeleniumDriver.close();
	}
}
