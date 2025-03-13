package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.Abstract_Components;

public class LandingPage extends Abstract_Components {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement user_Email;

	@FindBy(id = "userPassword")
	WebElement user_Password;

	@FindBy(name = "login")
	WebElement click_login;

	@FindBy(css = "div[class*='toast-error']")
	WebElement login_error_message;

	public ProductCataloguePage loginApplication(String email, String pass) {

		user_Email.sendKeys(email);
		user_Password.sendKeys(pass);
		click_login.click();

		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		return productCataloguePage;

	}

	public String login_error() {

		waitforWebElementToAppear(login_error_message);
		return login_error_message.getText();
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

	}

}
