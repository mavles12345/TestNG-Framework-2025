package com.test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

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

public class Error_Validation_HashMap extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "test_data")

	public void submitOrderTest(HashMap<String, String> input) throws IOException {

		// String productName = "ZARA COAT 3";

		ProductCataloguePage productCataloguePage = landinPage.loginApplication(input.get("email"), input.get("pass"));

		productCataloguePage.get_Products_List();

		productCataloguePage.addProductToCart(input.get("productName"));

		CartPage cartpage = productCataloguePage.goToCartPage();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		boolean match = cartpage.verifyCartProduct(input.get("productName"));
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

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "jackmavles@gmail.com");
		map.put("pass", "Selvam@18");
		map.put("productName", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "jackmavles@gmail.com");
		map1.put("pass", "Selvam@18");
		map1.put("productName", "LAPTOP");

		return new Object[][] { { map }, { map1 } };

	}

}
