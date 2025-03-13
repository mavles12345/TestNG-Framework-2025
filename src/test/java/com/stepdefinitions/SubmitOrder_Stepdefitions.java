package com.stepdefinitions;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;

import com.pageobjects.CartPage;
import com.pageobjects.CheckoutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCataloguePage;
import com.testcomponents.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SubmitOrder_Stepdefitions extends BaseTest {

	LandingPage landingPage;
	ProductCataloguePage productCataloguePage;
	CartPage cartpage;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmpage;

	@Given("User is on the E commerce website application")
	public void user_is_on_the_e_commerce_website_application() throws IOException {

		landingPage = launchApplication();

	}

	@When("User is logged with {string} and {string}")
	public void user_is_logged_with_and(String email, String pass) {
		productCataloguePage = landingPage.loginApplication(email, pass);
	}

	@When("User is adding the {string} into the cart")
	public void user_is_adding_the_into_the_cart(String productName) {

		productCataloguePage.addProductToCart(productName);

		cartpage = productCataloguePage.goToCartPage();

	}

	@When("User checkout the {string} and submit the order")
	public void user_checkout_the_and_submit_the_order(String productName) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		boolean match = cartpage.verifyCartProduct(productName);
		Assert.assertTrue(match);

		checkoutPage = cartpage.checkout_Ele();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		checkoutPage.selectCountry("India");
		confirmpage = checkoutPage.submitorder();

	}

	@Then("{string} message display in the confirmation page")
	public void message_display_in_the_confirmation_page(String message) {
		String confirmMessage = confirmpage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));

	}

}
