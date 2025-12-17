package APIChaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	static final String BASE_URL ="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN ="d000c4b40b1040eaf9582c6ce6b65b7dc6f975e9f99c913ead46cca24a050cb6";
	int userID;
	Faker faker= new Faker();
	@Test
	void createUser(ITestContext context)
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
		
		context.setAttribute("userID", userID);
	}
}
