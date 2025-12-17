package APIChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class GetUser {
	
	static final String BASE_URL ="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN ="d000c4b40b1040eaf9582c6ce6b65b7dc6f975e9f99c913ead46cca24a050cb6";
	@Test(dependsOnMethods= {"APIChaining.CreateUser.createUser"})
	void getUser(ITestContext context)
	{
		
		given()
		.headers("Authorization", "Bearer "+BEARER_TOKEN, null)
		.contentType("application/json")
		.pathParam("id", (Integer)context.getAttribute("userID"))
		.when()
		.get(BASE_URL+"/{id}")
		.then()
		.statusCode(200)
		.log().body();
	}

}
