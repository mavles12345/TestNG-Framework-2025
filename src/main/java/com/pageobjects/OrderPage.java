package com.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.Abstract_Components;

public class OrderPage extends Abstract_Components {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tbody tr td:nth-child(3)")
	List<WebElement> order_products;

	public boolean order_verify(String productName) {

		boolean match = order_products.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
