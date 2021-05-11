package com.challenge.qa.WebFEAutomation.glue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.challenge.qa.WebFEAutomation.driver.SeleniumDriver;

import io.cucumber.java.en.Given;

public class WebPages {

	private WebDriver driver;
    private WebDriverWait wait;

	public WebPages() {
		this.driver = SeleniumDriver.getCurrentDriver();
        this.wait = new WebDriverWait(driver, 20);
	}

	@Given("user access to {string} page")
	public void user_access_to_page(String string) {
		By homePageXpath = By.xpath("//a[contains(text(),'" + string + "')]"); 
		wait.until(ExpectedConditions.elementToBeClickable(homePageXpath));
		driver.findElement(homePageXpath).click();
	}
}
