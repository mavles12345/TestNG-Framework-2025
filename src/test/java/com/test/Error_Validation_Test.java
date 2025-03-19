package com.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.pageobjects.CartPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCataloguePage;
import com.sun.net.httpserver.Authenticator.Retry;
import com.testcomponents.BaseTest;

public class Error_Validation_Test extends BaseTest {

	@Test (groups= {"ErrorHandling"})
	//@Test
	public void login_error_Validation() throws IOException {

		// LandingPage landingpage = launchApplication();
		landinPage.loginApplication("jackmavles@gmail.com", "Selvam@1899");

		Assert.assertEquals(landinPage.login_error(), "Incorrect or password.");

	}

	//@Test(retryAnalyzer = (Retry.class)
	//@Test
	@Test (groups= {"ErrorHandling"})
	public void product_error_Validation() {

		String productName = "ZARA COAT 3";

		ProductCataloguePage productCataloguePage = landinPage.loginApplication("jackmavles@gmail.com", "Selvam@18");

		productCataloguePage.get_Products_List();

		productCataloguePage.addProductToCart(productName);

		CartPage cartpage = productCataloguePage.goToCartPage();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		boolean match = cartpage.verifyCartProduct("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
