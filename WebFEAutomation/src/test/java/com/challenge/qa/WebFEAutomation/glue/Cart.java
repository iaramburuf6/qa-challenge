package com.challenge.qa.WebFEAutomation.glue;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.qa.WebFEAutomation.driver.SeleniumDriver;
import com.google.common.base.Objects;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cart {
	
	final Logger LOG = LoggerFactory.getLogger(Cart.class);

	private WebDriver driver;
    private WebDriverWait wait;
    
    private double cartAmount;
    private double purchaseId;
    private double purchaseAmount;

	public Cart() {
		this.driver = SeleniumDriver.getCurrentDriver();;
		this.wait = new WebDriverWait(driver, 20);
		this.cartAmount = 0.0;
		this.purchaseId = 0.0;
		this.purchaseAmount = 0.0;
	}
	
	@Then("^user delete on cart the product \"([^\"]*)\"$")
	public void user_delete_on_cart_the_product(String product) {
		By deletProductXpath = By.xpath("//td[text()='" + product + "']//..//a[text()='Delete']"); 
		wait.until(ExpectedConditions.elementToBeClickable(deletProductXpath));
		driver.findElement(deletProductXpath).click();
	}
	
	@Then("user check cart data")
	public void user_check_cart_data() {
		By cartListColumnsXpath = By.xpath("//h2[text() = 'Products']//..//table/thead/tr/th"); 
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartListColumnsXpath));
		List<WebElement> cartListColumnElements = driver.findElements(cartListColumnsXpath);
		
		int priceColumnIndex = 0;
		for (int i = 1; i <= cartListColumnElements.size(); i++) {
			WebElement cartListColumnElement = cartListColumnElements.get(i - 1);
			String cartListColumName = cartListColumnElement.getText();
			if (Objects.equal("Price", cartListColumName)) {
				priceColumnIndex = i;
			}
		}
		
		By cartListRowXpath = By.xpath("//h2[text() = 'Products']//..//table/tbody/tr"); 
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartListRowXpath));
		List<WebElement> cartListRowElements = driver.findElements(cartListRowXpath);
		
		for (int i = 1; i <= cartListRowElements.size(); i++) {
			By cartProductXpath = By.xpath("//h2[text() = 'Products']//..//table/tbody/tr[" + i + "]/td[" + priceColumnIndex + "]");
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartProductXpath));
			String productPrice = driver.findElement(cartProductXpath).getText();
			
			this.cartAmount = this.cartAmount + Double.valueOf(productPrice);
		}
	}

	@Then("user click on place order")
	public void user_click_on_place_order() {
		By placeOrderXpath = By.xpath("//button[text()='Place Order']"); 
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderXpath));
		driver.findElement(placeOrderXpath).click();
	}
	
	@Then("user fill {string} field as {string}")
	public void user_fill_field_as(String string, String string2) {
		By fieldXpath = By.xpath("//input[@id='" + string + "']"); 
		wait.until(ExpectedConditions.elementToBeClickable(fieldXpath));
		driver.findElement(fieldXpath).sendKeys(string2);
	}

	@When("user click on purchase")
	public void user_click_on_purchase() {
		By purchaseXpath = By.xpath("//button[text()='Purchase']"); 
		wait.until(ExpectedConditions.elementToBeClickable(purchaseXpath));
		driver.findElement(purchaseXpath).click();
	}
	
	@Then("user check amount equals to expected")
	public void user_check_amount_equals_to_expected() {		
		By purchaseDataXpath = By.xpath("//p[contains(text(), 'Id:')]"); 
		wait.until(ExpectedConditions.elementToBeClickable(purchaseDataXpath));
		String purchaseData = driver.findElement(purchaseDataXpath).getText();
		
		getPurchaseData(purchaseData);
		
		assertEquals("The purchase amount (" + this.purchaseAmount + ") is not equal than cart amount ("
				+ this.cartAmount + ")", this.purchaseAmount, this.cartAmount, 0.01);
	}
	
	@Then("user click on ok")
	public void user_click_on_ok() {
		By okButtonXpath = By.xpath("//button[text() = 'OK']"); 
		wait.until(ExpectedConditions.elementToBeClickable(okButtonXpath));
		driver.findElement(okButtonXpath).click();
	}
	
	private void getPurchaseData(String purchaseData) {
		String[] data = purchaseData.split("\n");
		
		for (int i = 0; i < data.length; i++) {
			String atributeData = data[i];
			if (atributeData.contains("Id: ")) {
				String purchaseIdString = atributeData.replace("Id: ", "");
				this.purchaseId = Double.valueOf(purchaseIdString);
			}
			if (atributeData.contains("Amount: ")) {
				String purchaseAmountString = atributeData.replace("Amount: ", "");
				purchaseAmountString = purchaseAmountString.replace(" USD", "");
				this.purchaseAmount = Double.valueOf(purchaseAmountString);
			}
		}
		LOG.debug("The purchase id is " + this.purchaseId);
		LOG.debug("The purchase amount is " + this.purchaseAmount);
	}
}
