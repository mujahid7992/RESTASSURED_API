package APIChaining;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	static final String BASE_URL ="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN ="d000c4b40b1040eaf9582c6ce6b65b7dc6f975e9f99c913ead46cca24a050cb6";
	Faker faker= new Faker();
	@Test(dependsOnMethods= {"APIChaining.CreateUser.createUser"})
	void updateUser(ITestContext context)
	{
		
JSONObject requestdata = new JSONObject();
		
		requestdata.put("name", faker.name().fullName());
		requestdata.put("gender", "female");
			
		requestdata.put("status", "inactive");
		given()
		.headers("Authorization", "Bearer "+BEARER_TOKEN, null)
		.contentType("application/json")
		.pathParam("id", (Integer)context.getAttribute("userID"))
		.body(requestdata.toString())
		.when()
		.put(BASE_URL+"/{id}")
		.then()
		
		.statusCode(200)
		 .body("gender", equalTo("female"))
		    .body("status", equalTo("inactive"))
		.log().body();
	}
	

}
