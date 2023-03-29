package com.examples.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndPoints {
	
	static String param = "food";
	
	public static ArrayList<String> getItems() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("atm");
		list.add("food");
		list.add("cafe");
		list.add("shopping");
		list.add("attraction");
		list.add("lodging");
		list.add("default");
		return list;
	}

	public static Response responseURI() {
		RestAssured.baseURI = "https://coinmap.org/";
		RequestSpecification request = RestAssured.given();
		Response response = request.get("api/v1/venues/");
		return response;
	}

	public static HashMap<String, Integer> getCountOfCategory() {
		Response response = responseURI();
		JsonPath jsonPath = response.jsonPath();
		List<Categories> list = jsonPath.getList("venues", Categories.class);
		int atm = 0, food = 0, cafe = 0, shopping = 0, attraction = 0, lodging = 0, defaultCount = 0;
		ArrayList<String> items1 = getItems();
		for (int i = 0; i < items1.size(); i++) {
			for (Categories categories : list) {
				if ((categories.category.equals("atm")) && (categories.category.equals(items1.get(i)))) {
					atm++;
				} else if ((categories.category.equals("food")) && (categories.category.equals(items1.get(i)))) {
					food++;
				} else if ((categories.category.equals("cafe")) && (categories.category.equals(items1.get(i)))) {
					cafe++;
				} else if ((categories.category.equals("shopping")) && (categories.category.equals(items1.get(i)))) {
					shopping++;
				} else if ((categories.category.equals("lodging")) && (categories.category.equals(items1.get(i)))) {
					lodging++;
				} else if ((categories.category.equals("attraction")) && (categories.category.equals(items1.get(i)))) {
					lodging++;
				} else if ((categories.category.equals("default")) && (categories.category.equals(items1.get(i)))) {
					defaultCount++;
				}
			}
		}
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<String> items2 = getItems();
		for (int i = 0; i < items2.size(); i++) {
			if (items2.get(i).equals("atm")) {
				map.put(items2.get(i), atm);
			} else if (items2.get(i).equals("food")) {
				map.put(items2.get(i), food);
			} else if (items2.get(i).equals("cafe")) {
				map.put(items2.get(i), cafe);
			} else if (items2.get(i).equals("shopping")) {
				map.put(items2.get(i), shopping);
			} else if (items2.get(i).equals("attraction")) {
				map.put(items2.get(i), attraction);
			} else if (items2.get(i).equals("lodging")) {
				map.put(items2.get(i), lodging);
			} else if (items2.get(i).equals("default")) {
				map.put(items2.get(i), defaultCount);
			}
		}
		return map;
	}
}
