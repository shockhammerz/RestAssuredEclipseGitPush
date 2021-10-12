package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherAPI {
	@Test(description = "Getting weather information of Specific City")
	public void getWeather1() {
		RestAssured.given() // some Pre-Condition like authentication
				.when() // Perform some steps
				.get("https://api.openweathermap.org/data/2.5/weather?q=pune&appid=68d5d1da4d2400fce9dbfa37e712bcc4")
				.then()// Some Post-Condition like Verification
				.log() // prints data in console
				.body().statusCode(200);

	}

	@Test(enabled = true, description = "Getting weather information of Specific City")
	public void getWeather2() {
		Response res = RestAssured.given() // some Pre-Condition like authentication
				.when() // Perform some steps
				.get("https://api.openweathermap.org/data/2.5/weather?q=mumbai&appid=68d5d1da4d2400fce9dbfa37e712bcc4");
		System.out.println((res.prettyPrint()));
		System.out.println((res.getTime()));
		System.out.println((res.getStatusCode()));
		System.out.println((res.getContentType()));
	}
	@Test(enabled=true, description="Getting weather information of Specific City")
	public void getWeather3() {
		  		RestAssured.given()  //some Pre-Condition like authentication
		  			.queryParam("q", "Mumbai")
		  			.queryParam("appid", "68d5d1da4d2400fce9dbfa37e712bcc4")
		  			.when() //Performs some steps
		  			.get("https://api.openweathermap.org/data/2.5/weather")
		  			.then()
		  			.log() //print data in console
		  			.body()//printing body
		  			.statusCode(200);
		}
	
	@Test(enabled=true, description="Getting weather information of Specific City")
	public void getWeather4() {
		Map<String, String> param = new HashMap<String, String >();
		param.put("q", "Mumbai");
		param.put("appid", "68d5d1da4d2400fce9dbfa37e712bcc4");
		  		RestAssured.given()  //some Pre-Condition like authentication
		  		.queryParams(param)
		  	  .when()					// Performs some steps
		  	  .get("https://api.openweathermap.org/data/2.5/weather")
		  	  .then()			// Some Post_Condition like Verification
		  	  .log()			//Print data in Console
		  	  .body()			//Printing body
		  	  .statusCode(200);
		}
}
