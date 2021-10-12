package day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class GitHub {
	@BeforeTest
	public void beforeTest()
	{
		baseURI="https://api.github.com/user/repos";
		authentication=RestAssured.oauth2("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");
		}
  @Test(enabled=true)
  public void gettingAllRepositories() {
	  given()
	  		.auth()
	  		.oauth2("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")
	  	.when()
	  		.get("")
	  	.then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
	  @Test(enabled=true)
	  public void createRepositories() {
		  JSONObject data = new JSONObject();
		  
			data.put("name", "RestAssuredCreations2");
			data.put("description", "Created By RestAssured Tool");
			data.put("homepage", "https://github.com/shockhammerz");
		  
		  given()
		  		.auth()
		  		.oauth2("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")
		  		.header("Content-Type", "application/json")
		  		.body(data.toJSONString())
		  	.when()
		  		.post()
		  	.then()
		  		.log()
		  		.body()
		  		.statusCode(201);
	  }
	}
