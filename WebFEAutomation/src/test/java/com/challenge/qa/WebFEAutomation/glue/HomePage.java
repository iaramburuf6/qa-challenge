package com.challenge.qa.WebFEAutomation.glue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.challenge.qa.WebFEAutomation.driver.SeleniumDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePage {
	
	private WebDriver driver;
    private WebDriverWait wait;
    
	public HomePage() {
		this.driver = SeleniumDriver.getCurrentDriver();;
		this.wait = new WebDriverWait(driver, 20);
	}

	@When("user access to {string} category")
	public void user_access_to_category(String string) {
		By homeCategoryXpath = By.xpath("//a[text() = '" + string + "']"); 
		wait.until(ExpectedConditions.elementToBeClickable(homeCategoryXpath));
		driver.findElement(homeCategoryXpath).click();
	}

	@Then("^user select product \"([^\"]*)\"$")
	public void user_select_product(String product) {
		By homeProductXpath = By.xpath("//a[contains(text(),'" + product + "')]"); 
		wait.until(ExpectedConditions.elementToBeClickable(homeProductXpath));
		driver.findElement(homeProductXpath).click();
	}
	
	@Then("user add the product to cart")
	public void user_add_the_product_to_cart() {
	    By addToCart = By.xpath("//a[text() = 'Add to cart']");
	    wait.until(ExpectedConditions.elementToBeClickable(addToCart));
		driver.findElement(addToCart).click();
	}
	
	@Then("user close pop-up")
	public void user_close_pop_up() {
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

}
