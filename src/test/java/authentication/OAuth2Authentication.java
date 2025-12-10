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

public class OAuth2Authentication {
	/*
	 * Need client id and client secret to get the token 
	 * for this we need to post a request
	 * 
	 * 
	 */
	
	
	void verifyOAuth2Verification()
	{
		String clientID="clientID";
		String clientsecret="clientsecret";
		String tokenURL="tokenURL";
		String redirectURL="redirectURL";
		String grantType="grantType";
		String authirizationCode="authirizationCode";
		
		String token =given()
		.formParam("clientID", clientID)
		.formParam("clientsecret", clientsecret)
		.formParam("redirectURL", redirectURL)
		.formParam("grantType", grantType)
		.formParam("authirizationCode", authirizationCode)
		.when()
		.post(tokenURL)
		
		.then()
		.statusCode(200)
		.extract().jsonPath().get("accessToken");
		
		given()
		.auth().oauth2(token)
.when()
.post("url")
.then()
.statusCode(200);
	}
}
