package com.cucumber_runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "F:\\Eclipse-workspace-RestAssured-April2024\\TestNG-Framework-2025\\src\\main\\java\\com\\cucumber\\submitOrder.feature", glue = {
"com\\stepdefinitions" }, monochrome = true, dryRun = false, plugin = { "pretty", "html:target/Html_report.html",
"json:target/jsonreports/cucumber_report.json" })
public class SubmitOrder_Runner extends AbstractTestNGCucumberTests {

}
