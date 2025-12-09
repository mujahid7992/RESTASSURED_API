package authentication;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

public class typesOfAuthentication {
	@Test(enabled=false)
	void verifyBasicAuth()
	{
		given()
		.auth().basic("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));
		
	}
	@Test(enabled=false)
	void verifyBasicAuth_preemptive()
	{
		given()
		.auth().preemptive().basic("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));
		
	}
	@Test(enabled=false)
	void verifyDigest()
	{
		given()
		.auth().digest("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));
		
	}
	
	
	void verifyAPIKeyAuthentication()
	{
		given()
		.queryParam("page", 2)
		.header("x-api-key", "reqres_d0023efb031e4fc9a5f5be102fbc4544", null)
		 .when()
		 .get("https://reqres.in/api/users")
		 .then()
		 .statusCode(200)
		 .log().body();
		
	}
	@Test
	void verifyAPIKeyAuthentication_queryparams()
	{
		given()
		.queryParam("page", 2)
		.queryParam("x-api-key", "reqres_d0023efb031e4fc9a5f5be102fbc4544")
		 .when()
		 .get("https://reqres.in/api/users")
		 .then()
		 .statusCode(200)
		 .log().body();
		
	}
	
	

}
