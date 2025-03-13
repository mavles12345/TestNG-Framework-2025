package com.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReTry implements IRetryAnalyzer {

	int min = 0;
	int max = 3;

	@Override
	public boolean retry(ITestResult result) {

		if (min < max) {

			min++;
			return true;
		}

		return false;
	}

}
