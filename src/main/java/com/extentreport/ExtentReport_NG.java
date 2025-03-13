package com.extentreport;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport_NG {

	public static ExtentReports getReportObject() {

		File filepath = new File(System.getProperty("user.dir") + "//reports//index.html");

		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);

		reporter.config().setReportName("Web Automation Testing results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Manager", "Selvam");
		return extent;

	}

}
