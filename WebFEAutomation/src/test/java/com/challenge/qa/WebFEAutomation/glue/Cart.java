package com.challenge.qa.WebFEAutomation.glue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.challenge.qa.WebFEAutomation.driver.SeleniumDriver;

import io.cucumber.java.en.Then;

public class Cart {

	private WebDriver driver;
    private WebDriverWait wait;

	public Cart() {
		this.driver = SeleniumDriver.getCurrentDriver();;
		this.wait = new WebDriverWait(driver, 20);
	}
	
	@Then("^user delete on cart the product \"([^\"]*)\"$")
	public void user_delete_on_cart_the_product(String product) {
		By deletProductXpath = By.xpath("//td[text()='" + product + "']//..//a[text()='Delete']"); 
		wait.until(ExpectedConditions.elementToBeClickable(deletProductXpath));
		driver.findElement(deletProductXpath).click();
	}
}
