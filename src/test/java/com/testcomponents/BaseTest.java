package com.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landinPage;

	public WebDriver initializeBrowser() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"F:\\Eclipse-workspace-RestAssured-April2024\\TestNG-Framework-2025\\src\\main\\java\\com\\resources\\Global.properties");

		prop.load(fis);

		// global variables from maven to run different browser
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browser = prop.getProperty("browser");

		if (browser.contains("chrome")) {

			driver = new ChromeDriver();
		} else if (browser.contains("headless")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("headless");

			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browser.equals("firefox")) {

			driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else {

			driver = new EdgeDriver();
			driver.manage().window().setSize(new Dimension(1440, 900));
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		return driver;
	}

	public List<HashMap<String, String>> getJsonCode(String jsonpath) throws IOException {

		String jsonfilePath = FileUtils.readFileToString(new File(jsonpath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonfilePath,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File fis = new File(System.getProperty("user.dir") + "//Screenshot//" + testcasename + ".png");
		FileUtils.copyFile(source, fis);
		return System.getProperty("user.dir") + "//Screenshot//" + testcasename + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		initializeBrowser();
		landinPage = new LandingPage(driver);
		landinPage.goTo();
		return landinPage;

	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {

		driver.close();
	}

}
