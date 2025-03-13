package com.pageobjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponents.Abstract_Components;

public class ProductCataloguePage extends Abstract_Components {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = "ng-animating")
	WebElement spinner;

	By productBy = By.cssSelector(".mb-3");
	//By toast_container = By.id("toast-container");
	By toast_message=By.cssSelector("#toast-container");
	By addTocart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> get_Products_List() {

		waitforElementToAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = get_Products_List().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;

	}

	public void addProductToCart(String productName) {

		WebElement prod = getProductByName(productName);
		prod.findElement(addTocart).click();

		waitforElementToAppear(toast_message);
		waitforElementToDisAppear(spinner);

	}

}
