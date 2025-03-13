package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.Abstract_Components;

public class ConfirmationPage extends Abstract_Components {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	
	public String getConfirmationMessage() {
		
		return message.getText();
	}
	

}
