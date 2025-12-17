package cookies;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

public class Headers {

	@Test
	void testHeaders()
	{
		Response response=
		given()
		.when()
		.get("https://www.google.com")
		.then()
		.log().headers()
		.statusCode(200)
		.header("Content-Type", containsString("text/html"))
		.header("Content-Encoding", notNullValue())
		.extract().response()
		
		;
		
		io.restassured.http.Headers headers = response.getHeaders();
		
		for(Header head: headers)
		{
			System.out.println(head.getName()+": "+ head.getValue());
		}
		
		
		
		
		
		
		
		
		
	}
}

