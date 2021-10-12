package com.restassured.testcases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.restassured.constants.Constants;
import com.restassured.requests.pojo.PostContentItemRequest;
import com.restassured.responses.pojo.PostContentItemResponse;
import com.restassured.utils.CsvDataProvider;
import com.restassured.utils.RandomUtils;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContentItemTests extends BaseTest {

	@Test(dataProvider = "ContentItemData", dataProviderClass = CsvDataProvider.class)
	public void getContentItemTest(Map<String, String> data) throws IOException {
		/*
		 * Replaces a project_id parameter in an endpoint with the data from a CSV file. Adds an value of an itemId column at the end of the endpoint from the CSV file. Data
		 * provider returns a hashmap and a column name is used as a key to get the value.
		 */
		Response response = given().header("Authorization", "Bearer " + data.get("authBearerToken"))
				.filter(new RequestLoggingFilter(captor)).log().all()
				.get((Constants.BASE_URL + Constants.CONTENT_ITEM_ENDPOINT + data.get("itemId")).replace("{project_id}",
						data.get("projectId")));
		
		//Writes a request and response into an extent report.		
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

		// Asserts a content item ID in the response using a jsonPath method.
		// Expects a value from an itemId column.
		Assert.assertEquals(response.jsonPath().get("id"), data.get("itemId"));

		// Writes a response into an log file.
		Files.write(response.asByteArray(),
				new File(Constants.RESPONSE_TXT_PATH + data.get("TestCaseName") + data.get("itemCodeName") + ".txt"));

	}

	@Test(dataProvider = "ContentItemData", dataProviderClass = CsvDataProvider.class)
	public void postContentItemTest_WithoutPojo(Map<String, String> data) {
		/*
		 * Sample request body: { "name", "codeName", "type" { "codename" } }
		 */
		Map<String, Object> mapObj = new HashMap<String, Object>();
		Map<String, Object> typeObj = new HashMap<String, Object>();
		// Gets values from a CSV file as a user input.
		typeObj.put("codename", data.get("typeCodeName")); 
		mapObj.put("name", data.get("itemName")); 
		mapObj.put("codeName", "codename" + RandomUtils.generateRandomNumericString(5));
		mapObj.put("type", typeObj); 

		Response response = given().filter(new RequestLoggingFilter(captor)) // This line is mandatory to log the request details for an extent report
				.header("Authorization", "Bearer " + data.get("authBearerToken"))
				.header("Content-Type", "application/json").contentType(ContentType.JSON).log().all()
				.body(mapObj) // Passing mapObj in request body
				.post(Constants.BASE_URL + Constants.CONTENT_ITEM_ENDPOINT.replace("{project_id}", data.get("projectId"))); // Posting request

		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

		// Asserts a status code.
		response.then().statusCode(201);

		Assert.assertEquals(response.jsonPath().get("name"), data.get("itemName"));

	}

	@Test(dataProvider = "ContentItemData", dataProviderClass = CsvDataProvider.class)
	public void postContentItemTest_WithPojo(Map<String, String> data) {

		/*
		 * Creates a POJO class for a request. 
		 * Creates an object and initiates all class variables using a constructor.
		 * Passes them into the body of the request.
		 */

		Map<String, String> typeObj = new HashMap<String, String>();
		typeObj.put("codename", data.get("typeCodeName")); 
		
		PostContentItemRequest obj = new PostContentItemRequest(data.get("itemName"), null, typeObj, null, null);

		Response response = given().filter(new RequestLoggingFilter(captor))
				.header("Authorization", "Bearer " + data.get("authBearerToken"))
				.header("Content-Type", "application/json").contentType(ContentType.JSON).log().all()
				.body(obj)
				.post(Constants.BASE_URL + Constants.CONTENT_ITEM_ENDPOINT.replace("{project_id}", data.get("projectId")));

		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

		response.then().statusCode(201);

		Assert.assertEquals(response.jsonPath().get("name"), data.get("itemName"));

	}

	@Test(dataProvider = "ContentItemData", dataProviderClass = CsvDataProvider.class)
	public void postContentItemTest_ByReadingRequestFromFile(Map<String, String> data) throws IOException {

		// Reads a request body directly from a JSON file and passing the data to the body as a string.
		Response response = given().filter(new RequestLoggingFilter(captor))
				.header("Authorization", "Bearer " + data.get("authBearerToken"))
				.header("Content-Type", "application/json").contentType(ContentType.JSON).log().all()
				.body(generateStringFromResource(Constants.REQUEST_JSON_FOLDER_PATH + "request_post_content_item.json")) 
				.post(Constants.BASE_URL + Constants.CONTENT_ITEM_ENDPOINT.replace("{project_id}", data.get("projectId"))); 

		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

		response.then().statusCode(201);

		PostContentItemResponse resobj = response.as(PostContentItemResponse.class);

		Assert.assertEquals(resobj.getName(), data.get("itemName"));
	}
}
