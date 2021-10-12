package com.restassured.reports;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.restassured.constants.Constants;

public class ExtentReport {
	private static ExtentReports report = null;
	private static String extentreportpath = "";

	private ExtentReport() {
		extentreportpath = Constants.EXTENT_REPORT_PATH;
		report = new ExtentReports(extentreportpath);
		report.loadConfig(new File(Constants.EXTENT_CONFIG_FILE_PATH));
	}

	public static void initialize() {
		new ExtentReport();
	}
	
	public static ExtentReports getReport() {
		return report;
	}
	
}
