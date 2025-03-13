package com.test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

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

public class Error_Validation_HashMap_Json extends BaseTest {

	@Test(dataProvider = "test_data")

	public void submitOrderTest(HashMap<String, String> input) throws IOException {

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
	public Object[][] test_data() throws IOException {

		List<HashMap<String, String>> data = getJsonCode(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\jsonfiles\\purchaseorderdata.json\\");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
