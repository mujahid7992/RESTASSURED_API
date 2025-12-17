package Req_Res_Specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Reusability {
	
	RequestSpecification httpRequest;
	ResponseSpecification httpResponse;
	@BeforeClass
	public void setUp()
	{
		//create request specification
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setBaseUri("http://localhost:3000");
		reqBuilder.setBasePath("/employees");
		httpRequest=reqBuilder.build();
		
		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectHeader("Content-Type", "application/json");
		httpResponse= responseBuilder.build();
		
		
		
		
		
	}
	@Test
	void getAllEmployees()
	{
		given()
		.spec(httpRequest)
		.when()
		.get()
		.then()
		.spec(httpResponse)
		.log().body()
		;
		
	}

}
