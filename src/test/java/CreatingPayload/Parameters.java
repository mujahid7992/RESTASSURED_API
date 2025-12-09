package CreatingPayload;

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
public class Parameters {

	@Test
	void pathParams()
	{
		given()
		.pathParam("country", "India")
		.when()
		.get("https://restcountries.com/v2/name/{country}")
		.then()
		.statusCode(200)
		.log().body();	
	}
	@Test
	void queryParam()
	{
		given()
		.contentType("application/json")
		.queryParam("page", 2)
		.queryParam("id", 5)
		.header("x-api-key", "reqres_d0023efb031e4fc9a5f5be102fbc4544", null)
		.when()
			.get("https://reqres.in/api/users")
		.then()
		.statusCode(200)
		.log().body()
		;
	}
}
