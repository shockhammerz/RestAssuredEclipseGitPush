package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PositiveContactList {
	String id;

	@Test(enabled = false, description = "Getting all Contact List")
	public void getContactListenInfo() {
		given().when().get("http://3.13.86.142:3000/contacts").then().log().body().statusCode(200);

	}

	@Test(enabled = false, description = "Getting specific Contact")
	public void getSpecficConatact() {
		given().when().get("http://3.13.86.142:3000/contacts/60eba23e170734047659ad54").then().log().body()
				.statusCode(200);

	}

	@Test(enabled = false, description = "Getting specific Contact")
	public void getSpecficConatact2() {
		Response res = given().when().get("http://3.13.86.142:3000/contacts/615fcbaff2967f0ec893aeca");

		System.out.println(res.getTime());
		res.then().log().body().statusCode(200);
	}

	@Test(enabled = true, description = "Getting specific Contact List")
	public void postAddingContact() {

		JSONObject details = new JSONObject();
		JSONObject loc = new JSONObject();
		JSONObject emp = new JSONObject();

		loc.put("city", "Mumbai");
		loc.put("country", "india");
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		details.put("firstName", "vaibs");
		details.put("lastName", "rock");
		details.put("email", "vaibs99@lti.com");
		details.put("location", loc);
		details.put("employer", emp);

		ExtractableResponse<Response> ex = given().header("Content-Type", "application/json")
				.body(details.toJSONString()).when().post("http://3.13.86.142:3000/contacts").then().log().body()
				.statusCode(200).extract();// .path("_id");
		id = ex.path("_id");
		System.out.println(ex.path("_id"));
		System.out.println(ex.path("firstName"));
		System.out.println(ex.path("lastName"));
		System.out.println(ex.path("location.city"));

	}

	@Test(enabled = true, dependsOnMethods="postAddingContact" ,description = "Getting specific Contact")
	public void postUpdateContact() {

		JSONObject details = new JSONObject();
		JSONObject loc = new JSONObject();
		JSONObject emp = new JSONObject();

		loc.put("city", "Mumbai");
		loc.put("country", "India");
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		details.put("firstName", "Mohit");
		details.put("lastName", "pawar");
		details.put("email", "mohit@lti.com");
		details.put("location", loc);
		details.put("employer", emp);

		given()
		 	.header("Content-Type","application/json")
		 	.body(details.toJSONString())
		 .when()
		 	.put("http://3.13.86.142:3000/contacts/"+id)
		 .then()
		 	.log()
		 	.body()
		 	.statusCode(204);
	}
	@Test(enabled = true, dependsOnMethods="postUpdateContact" ,description = "Getting specific Contact")
	public void getSpecficConatct3() {
		 given()
		  .when()
		  .get("http://3.13.86.142:3000/contacts/"+id)
		  .then()
		  .log()
		  .body()
		  .statusCode(200);
	}
	@Test(enabled = true, dependsOnMethods="getSpecficConatct3" ,description = "Deleting specific Contact")
	public void deletingSpecificContact() {
		 given()
		  .when()
		  	.delete("http://3.13.86.142:3000/contacts/"+id)
		  .then()
		  	.log()
		  	.body()
		  	.statusCode(204);
	}
	@Test(enabled = true, dependsOnMethods="deletingSpecificContact" ,description = "Getting specific Contact for Deleted Content")
	public void getSpecificContact4() {
		 given()
		  .when()
		  	.delete("http://3.13.86.142:3000/contacts/"+id)
		  .then()
		  	.log()
		  	.body()
		  	.statusCode(404);
	}
}
