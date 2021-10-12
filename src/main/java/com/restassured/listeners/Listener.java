package com.restassured.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.restassured.reports.ExtentManager;
import com.restassured.reports.ExtentReport;
import com.restassured.reports.LogStatus;

/*
 * Listener class implements ITestListener to dynamically create reports and write logs.
 */
public class Listener implements ITestListener {

	private String TestcaseName;

	public String getTestcaseName() {
		return TestcaseName;
	}

	public void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	public void onTestStart(ITestResult result) {
		setTestcaseName(result.getName());
		ExtentManager.setExtentTest(ExtentReport.getReport().startTest(TestcaseName));
		LogStatus.pass(TestcaseName + " is started successfully");
	}

	public void onTestSuccess(ITestResult result) {
		LogStatus.pass(result.getName() + " is passed");
		ExtentReport.getReport().endTest(ExtentManager.getExtTest());
	}

	public void onTestFailure(ITestResult result) {
		LogStatus.fail(result.getName() + " is failed");
		LogStatus.fail(result.getThrowable().toString());
		ExtentReport.getReport().endTest(ExtentManager.getExtTest());
	}

	public void onTestSkipped(ITestResult result) {
		LogStatus.skip(result.getName() + " is skipped");
		ExtentReport.getReport().endTest(ExtentManager.getExtTest());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentReport.getReport().endTest(ExtentManager.getExtTest());
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
		ExtentReport.getReport().endTest(ExtentManager.getExtTest());
	}

}
