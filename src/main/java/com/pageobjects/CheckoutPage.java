package com.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.Abstract_Components;

public class CheckoutPage extends Abstract_Components {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement addcountry;

	@FindBy(css = "button[class*='ta-item']:nth-child(3)")
	WebElement select_Country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By country_List = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		addcountry.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		addcountry.sendKeys(countryName);

		// Actions a = new Actions(driver);
		// a.sendKeys(addcountry, countryName).perform();

		waitforElementToAppear(country_List);
		select_Country.click();

		// country_list.stream().filter(countr->countr.getText().equalsIgnoreCase(country));

	}

	public ConfirmationPage submitorder() {

		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);

		return confirmationPage;

	}

}
