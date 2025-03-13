package com.standalonetest;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Standalone_E_Commerce {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("jackmavles@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selvam@18");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));// wait for text visible in
																							// footer

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("LAPTOP"))
				.findFirst().orElse(null);
		
		Thread.sleep(5000);

		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();

		driver.findElement(By.id("toast-container"));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));// wait for text visible in
																							// footer

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));// wait for
																										// dissappear of
																										// spinnng wheel

		driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cart'] h3"));// list of added
																									// items in cart

		boolean match = cartProducts.stream().anyMatch(item -> item.getText().equalsIgnoreCase("IPHONE 13 PRO"));// check
																													// item
																													// added
																													// in
																													// cart

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();// checkout button click

		WebElement country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));

		Actions a = new Actions(driver);

		a.sendKeys(country, "India").perform();// send country details

		driver.findElement(By.cssSelector("button[class*='ta-item']:nth-child(3)")).click();// country select

		driver.findElement(By.cssSelector(".action__submit")).click();// click placeholder

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();// confirmation message

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		// driver.close();

	}
}
