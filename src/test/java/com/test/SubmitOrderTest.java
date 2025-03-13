package com.test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.CartPage;
import com.pageobjects.CheckoutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCataloguePage;
import com.testcomponents.BaseTest;

public class SubmitOrderTest {

	@Test

	public void submitOrderTest() throws IOException {

		String productName = "ZARA COAT 3";

		WebDriver driver = new ChromeDriver();

		LandingPage login = new LandingPage(driver);
		login.goTo();

		ProductCataloguePage productCataloguePage = login.loginApplication("jackmavles@gmail.com", "Selvam@18");

		productCataloguePage.addProductToCart(productName);

		CartPage cartpage = productCataloguePage.goToCartPage();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		boolean match = cartpage.verifyCartProduct(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartpage.checkout_Ele();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmpage = checkoutPage.submitorder();
		String confirmMessage = confirmpage.getConfirmationMessage();
		// Assert.assertTrue(confirmMessage, "Thankyou for the order.");
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}