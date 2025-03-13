package com.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.Abstract_Components;

public class CartPage extends Abstract_Components {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//li[@class='totalRow'] /button")
	WebElement checkout_Button;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	//By toast_container = By.id("toast-container");

	public boolean verifyCartProduct(String productName) {

	//	waitforElementToAppear(toast_container);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage checkout_Ele() {

		checkout_Button.click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;
	}

}
