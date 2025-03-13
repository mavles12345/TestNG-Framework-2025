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
import com.pageobjects.OrderPage;
import com.pageobjects.ProductCataloguePage;
import com.testcomponents.BaseTest;

public class SubmitOrderTest_Orderverifty extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test

	public void submitOrderTest() throws IOException {

		// String productName = "ZARA COAT 3";

		ProductCataloguePage productCataloguePage = landinPage.loginApplication("jackmavles@gmail.com", "Selvam@18");

		productCataloguePage.get_Products_List();

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

	@Test(dependsOnMethods = "submitOrderTest")
	public void order_display() {

		ProductCataloguePage productCataloguePage = landinPage.loginApplication("jackmavles@gmail.com", "Selvam@18");

		OrderPage orderPage = productCataloguePage.goToOrderPage();

		orderPage.order_verify(productName);

	}

}