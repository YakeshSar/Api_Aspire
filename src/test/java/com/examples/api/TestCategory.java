package com.examples.api;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TestCategory extends EndPoints {

	@BeforeMethod()
	public void beforeMethod() {
		System.out.println("start the execution");
	}

	@Test(priority = 0)
	public void verifyGetMethod() {
		Response response = responseURI();
		System.out.println(response.statusCode());
		assertEquals(200, response.statusCode());
	}

	@Test(priority = 1)
	public void getCount() {
		HashMap<String, Integer> count = getCountOfCategory();
		Set<Entry<String, Integer>> entrySet = count.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
	}

	@Test(priority = 2)
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

	@AfterMethod
	public void afterMethod() {
		System.out.println("end the execution");
	}

}
