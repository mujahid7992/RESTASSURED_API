package cookies;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
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

public class Cookies {

	@Test
	void testCookies()
	{
		Response response=
		given()
		.when()
		.get("https://www.google.com")
		.then()
		.log().cookies()
		.statusCode(200)
		.cookie("AEC",notNullValue())
		.extract().response();
		
		//Extract a specific cookie
		
		String cookieValue= response.getCookie("AEC");
		System.out.println(cookieValue);
		
		Map <String,String> cookies =response.getCookies();
		
		for(String key:cookies.keySet())
		{
			System.out.println(key + " :"+cookies.get(key));
		}
		
		//get detailed information of cookie
		
		
		Cookie cookie_info= response.getDetailedCookie("AEC");
		System.out.println(cookie_info.getExpiryDate());
		
		
	}
}
