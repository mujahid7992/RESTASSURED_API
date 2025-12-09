package httpsmethods;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;




public class Httpmethods {
	int userID;
/*
 
 --api key --reqres_d0023efb031e4fc9a5f5be102fbc4544
 pre condition --give()--> contengt type,set cookies,add auth,add param,set headers info,pay;load is also a pre condition
 
 Action/steps --- when() --> get ,post,put ,delete
 
 validation --then()  validation status code
 */
	
	
	@Test(priority=1)
	
	public void getUsers()
	{
		given()
		.header("x-api-key", "reqres_d0023efb031e4fc9a5f5be102fbc4544", null)
		 .when()
		 .get("https://reqres.in/api/users?page=2")
		 .then()
		   .statusCode(200)
		    .body("page", equalTo(2))
		    .body(containsString("email"))
		   // .log().all()
		    ;
	}
	@Test(priority=2)
	public void post()
	{
		
		HashMap<String,String> map = new HashMap<>();
		map.put("name", "Mujahid");
		map.put("job", "Software engineer");
		
		userID=given()
		.contentType("application/json")
		.body(map)
		.header("x-api-key", "reqres_d0023efb031e4fc9a5f5be102fbc4544", null)
		 .when()
		 .post("https://reqres.in/api/users")
		 .then()
		   .statusCode(201)
		    .body("name", equalTo("Mujahid"))
		    .body("job", equalTo("Software engineer"))
		    .body(containsString("id"))
		    
		  //  .log().all()
		    .extract().jsonPath().getInt("id");
		    System.out.println(userID);
		    ;
	}
	@Test(priority=3)
	public void updateUser()
	{
		
		HashMap<String,String> map = new HashMap<>();
		map.put("name", "Mujahid");
		map.put("job", "Standup comedian");
		
		given()
		.contentType("application/json")
		.body(map)
		.header("x-api-key", "reqres_d0023efb031e4fc9a5f5be102fbc4544", null)
		 .when()
		 .put("https://reqres.in/api/users/"+userID)
		 .then()
		   .statusCode(200)
		    .body("name", equalTo("Mujahid"))
		    .body("job", equalTo("Standup comedian")) 
		    .body(containsString("id"))
		    
		    //.log().all()
		    ;
	}
	@Test(priority=4)
	public void deleteUser()
	{
		
		
		
		given()
		.contentType("application/json")
		.header("x-api-key", "reqres_d0023efb031e4fc9a5f5be102fbc4544", null)
		 .when()
		 .delete("https://reqres.in/api/users"+userID)
		 .then()
		   .statusCode(204)
		    
		    .body(emptyOrNullString())
		    
		    .log().all()
		    ;
	}
	
	
	
} 
