package com.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.CartPage;
import com.pageobjects.CheckoutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCataloguePage;
import com.testcomponents.BaseTest;

public class Error_Validation_Parameters extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "test_data")

	public void submitOrderTest(String email, String pass, String productName) throws IOException {

		// String productName = "ZARA COAT 3";

		ProductCataloguePage productCataloguePage = landinPage.loginApplication(email, pass);

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
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@DataProvider
	public Object[][] test_data() {

		return new Object[][] { 
				{ "jackmavles@gmail.com", "Selvam@18", "ZARA COAT 3" },
				{ "jackmavles@gmail.com", "Selvam@18", "LAPTOP" }
			//	{ "jackmavles@gmail.com", "Selvam@18", "IPHONE 13 PRO" }

		};

	}

}
