package com.restassured.requests.pojo;

import java.util.Map;

public class PostContentItemRequest {

	private String name;
	private String codeName;
	private Map<String, String> type;
	private Map<String, String> collection;
	private String external_id;

	public PostContentItemRequest(String name, String codeName, Map<String, String> type,
			Map<String, String> collection, String external_id) {
		this.name = name;
		this.codeName = codeName;
		this.type = type;
		this.collection = collection;
		this.external_id = external_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Map<String, String> getType() {
		return type;
	}

	public void setType(Map<String, String> type) {
		this.type = type;
	}

	public Map<String, String> getCollection() {
		return collection;
	}

	public void setCollection(Map<String, String> collection) {
		this.collection = collection;
	}

	public String getExternal_id() {
		return external_id;
	}

	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	@Override
	public String toString() {
		return "Post Content Item Request API: [name = " + name + ", codeName = " + codeName + ", type = " + type
				+ ", collection = " + collection + ", external_id = " + external_id + "]";
	}

}
