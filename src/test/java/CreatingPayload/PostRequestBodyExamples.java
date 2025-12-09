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

public class PostRequestBodyExamples {
	
	/*Different ways to create a post request
	 1.hash map
	 2.using org.json library
	 3.using pojo class
	 4.using external json file
	  
	 
	 */
	String studentID;
	@Test(enabled=false)
	void createStudentsUsingHashMap()
	{
		
		HashMap<String,Object> requestBody=new HashMap<>();
		
		requestBody.put("name", "Nouman");
		requestBody.put("id", 225);
		requestBody.put("location", "Hyderabad");
		
		List<String> courseList = new ArrayList<>();
		courseList.add("Running");
		courseList.add("Screaming");

		requestBody.put("courses", courseList);

		baseURI ="http://localhost:3000";
		
		studentID=given()
		.baseUri(baseURI)
		.contentType(ContentType.JSON)
		.body(requestBody)
					
		.when()
		.post("/students")
		
		.then()
		.statusCode(201)
		.log().all()
		.extract().jsonPath().getString("id");
		
		
	}
	@Test
	void createRequestBodyUsingJsonLibrary()
	{
		JSONObject requestBody = new JSONObject();
		
		requestBody.put("name", "Soni");
		requestBody.put("id", "300");
		requestBody.put("location", "Attapur");
		
		List<String> courseList = new ArrayList<>();
		courseList.add("Reading");
		courseList.add("Cooking");

		requestBody.put("courses", courseList);
		
		baseURI ="http://localhost:3000";
		
		studentID=given()
		.baseUri(baseURI)
		.contentType(ContentType.JSON)
		.body(requestBody.toString())//need to convert to string
					
		.when()
		.post("/students")
		
		.then()
		.statusCode(201)
		.log().all()
		.extract().jsonPath().getString("id");

		
	}
@Test	
void CreateStudentPojoClass()
{
	StudentPojo requestBody = new StudentPojo();
	requestBody.setName("Sandeep");
	requestBody.setLocation("Chennai");
	requestBody.setId("123");
	String courses[]= {"C","C++"};
	
	requestBody.setCourses(courses);
	baseURI ="http://localhost:3000";
	studentID=given()
	.baseUri(baseURI)
	.contentType(ContentType.JSON)
	.body(requestBody)//need to convert to string
				
	.when()
	.post("/students")
	
	.then()
	.statusCode(201)
	.log().all()
	.extract().jsonPath().getString("id");
	
	
}
@Test
void CreateStudentUsingExternalFile() throws FileNotFoundException
{
	File myFile = new File(".\\src\\test\\java\\body.json");
	FileReader fileReader = new FileReader(myFile);
	JSONTokener jsonTokener = new JSONTokener(fileReader);
	JSONObject requestBody = new JSONObject(jsonTokener);
	
	
			
	
	baseURI ="http://localhost:3000";
	studentID=given()
	.baseUri(baseURI)
	.contentType(ContentType.JSON)
	.body(requestBody.toString())//need to convert to string
				
	.when()
	.post("/students")
	
	.then()
	.statusCode(201)
	.log().all()
	.extract().jsonPath().getString("id");

}
@AfterMethod
void deleteStudent()
{
	given()
	.when()
	.delete("http://localhost:3000/students/"+studentID)
	.then()
	.statusCode(200);
	

}
}
