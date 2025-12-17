package APIChaining;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import org.json.JSONObject;

import static org.hamcrest.Matchers.greaterThan;

public class chainingAPIS {
	
	static final String BASE_URL ="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN ="d000c4b40b1040eaf9582c6ce6b65b7dc6f975e9f99c913ead46cca24a050cb6";
	int userID;
	Faker faker= new Faker();
	@Test
	void createUser()
	{
		
		JSONObject requestdata = new JSONObject();
		
		requestdata.put("name", faker.name().fullName());
		requestdata.put("gender", "Male");
		requestdata.put("email", faker.internet().safeEmailAddress());
		requestdata.put("status", "active");
		
		
		
		
		userID =given()
		.headers("Authorization", "Bearer "+BEARER_TOKEN, null)
		.contentType("application/json")
		.body(requestdata.toString())
		
		.when()
		.post(BASE_URL)
		.then()
		.statusCode(201)
		.log().body()
		.extract().response().jsonPath().getInt("id");
		
		;
		
		
	}
	@Test(dependsOnMethods= {"createUser"})
	void getUser()
	{
		
		given()
		.headers("Authorization", "Bearer "+BEARER_TOKEN, null)
		.contentType("application/json")
		.pathParam("id", userID)
		.when()
		.get(BASE_URL+"/{id}")
		.then()
		.statusCode(200)
		.log().body();
	}
	@Test(dependsOnMethods= {"getUser"})
	void updateUser()
	{
		
JSONObject requestdata = new JSONObject();
		
		requestdata.put("name", faker.name().fullName());
		requestdata.put("gender", "female");
			
		requestdata.put("status", "inactive");
		given()
		.headers("Authorization", "Bearer "+BEARER_TOKEN, null)
		.contentType("application/json")
		.pathParam("id", userID)
		.body(requestdata.toString())
		.when()
		.put(BASE_URL+"/{id}")
		.then()
		
		.statusCode(200)
		 .body("gender", equalTo("female"))
		    .body("status", equalTo("inactive"))
		.log().body();
	}
	@Test(dependsOnMethods= {"updateUser"})
	void deleteUser()
	{
		given()
		.headers("Authorization", "Bearer "+BEARER_TOKEN, null)
		.pathParam("id", userID)
		.when()
		.delete(BASE_URL+"/{id}")
		.then()
		.statusCode(204);
	

}
}