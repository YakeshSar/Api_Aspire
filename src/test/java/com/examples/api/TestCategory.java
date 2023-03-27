package com.examples.api;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCategory {

	static String param = "food";

	public static Response responseURI() {
		RestAssured.baseURI = "https://coinmap.org/";
		RequestSpecification request = RestAssured.given();
		Response response = request.get("api/v1/venues/");
		return response;
	}

	public static int getCount() {
		Response response = responseURI();
		JsonPath jsonPath = response.jsonPath();
		List<Categories> list = jsonPath.getList("venues", Categories.class);
		int count = 0;
		for (Categories categories : list) {
			if (categories.category.equals(param)) {
				count++;
			}
		}
		return count;
	}

	@Test
	public void verifyGetMethod() {
		Response response = responseURI();
		System.out.println(response.statusCode());
		assertEquals(200, response.statusCode());
	}

	@Test
	public void getCountOfCategory() {
		switch (param) {
		case "atm":
			System.out.println(getCount());
			break;
		case "food":
			System.out.println(getCount());
			break;
		case "cafe":
			System.out.println(getCount());
			break;
		case "shopping":
			System.out.println(getCount());
			break;
		case "attraction":
			System.out.println(getCount());
			break;
		case "lodging":
			System.out.println(getCount());
			break;
		case "default":
			System.out.println(getCount());
			break;
		default:
			System.out.println("Invalid Query");
			break;
		}
	}

	@Test
	public void getGeoLocationForFoodCategory() {
		Response response = responseURI();
		JsonPath jsonPath = response.jsonPath();
		List<Categories> list = jsonPath.getList("venues", Categories.class);
		for (Categories categories : list) {
			if (categories.category.equals(param)) {
				if (param.equals("food")) {
					System.out.println("[" + categories.name + " : " + categories.geolocation_degrees + "]");
				}
			}
		}
	}
}
