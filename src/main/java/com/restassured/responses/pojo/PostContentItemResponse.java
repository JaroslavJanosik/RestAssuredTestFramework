package com.restassured.responses.pojo;

import java.util.ArrayList;
import java.util.Map;

public class PostContentItemResponse {

	private String name;
	private String codename;
	private Map<String, String> type;
	private Map<String, String> collection;
	private ArrayList<String> sitemap_locations;
	private String external_id;
	private String last_modified;

	public PostContentItemResponse(String name, String codeName, Map<String, String> type,
			Map<String, String> collection, ArrayList<String> sitemap_locations, String external_id, String last_modified) {
		this.name = name;
		this.codename = codeName;
		this.type = type;
		this.collection = collection;
		this.sitemap_locations = sitemap_locations;
		this.external_id = external_id;
		this.last_modified = last_modified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codename;
	}

	public void setCodeName(String codeName) {
		this.codename = codeName;
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

	public ArrayList<String> getSitemap_locations() {
		return sitemap_locations;
	}

	public void setSitemap_locations(ArrayList<String> sitemap_locations) {
		this.sitemap_locations = sitemap_locations;
	}

	public String getExternal_id() {
		return external_id;
	}

	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	public String getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(String last_modified) {
		this.last_modified = last_modified;
	}

	@Override
	public String toString() {
		return "Post Content Item Response API: [name = " + name + ", codeName = " + codename + ", type = " + type
				+ ", collection = " + collection + ", sitemap_locations = " + sitemap_locations + ",  external_id = "
				+ external_id + ", last_modified = " + last_modified + "]";
	}
}
