package com.restassured.constants;

public class Constants {

	private Constants() {}
	
	public static final String BASE_URL = "https://manage.kontent.ai/";
	public static final String CONTENT_ITEM_ENDPOINT = "v2/projects/{project_id}/items/";

	public static final String CSV_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata.csv";
	public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "/ExtentReports/index.html";
	public static final String EXTENT_CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/extentreport.xml";
	
	public static final String REQUEST_JSON_FOLDER_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/jsonsforrequestbody/";
	public static final String SCHEMA_VALIDATION_JSON_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/jsonsforschemavalidations/content_items_response_schema.json";
	public static final String RESPONSE_TXT_PATH = "./output/responses/";
	public static final String JSONS_LOCATION = System.getProperty("user.dir") + "/src/test/resources/jsons";
	
}
