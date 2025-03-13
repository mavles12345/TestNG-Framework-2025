package com.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageobjects.CartPage;
import com.pageobjects.OrderPage;

public class Abstract_Components {

	WebDriver driver;

	public Abstract_Components(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink*=cart]")
	WebElement cart_Header;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement order_Header;

	public void waitforElementToAppear(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));// wait for text visible in footer

	}

	public void waitforWebElementToAppear(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));// wait for text visible in footer

	}

	public void waitforElementToDisAppear(WebElement ele) {

		// Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));// wait for text visible in footer

	}

	public CartPage goToCartPage() {

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", cart_Header);
	//	cart_Header.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

public OrderPage goToOrderPage() {
	
	order_Header.click();
	OrderPage orderpage=new OrderPage(driver);
	return orderpage;
	
	
}
}
