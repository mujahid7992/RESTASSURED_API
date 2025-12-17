package Serilazation_Deserialization;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
public class Serialization_deserialization_example {
String studentID;

@Test	
public void testserialization()
{
	String courses[]= {"Selenium","java"};
	student_serialization obj = new student_serialization("John","Hyderabad",courses,"34");
	
	studentID=given()
	
	.contentType("application/json")
	.body(obj)//serialization happens
	
	.when()
	.post("http://localhost:3000/students")
	.then()
	.statusCode(201)
	.extract().response().jsonPath().getString("id")
	;
	
	
}

public void deleteUser()
{
	given()
	.pathParam("id", studentID)
	.when()
	.delete("http://localhost:3000/students/{id}")
	.then()
	.statusCode(200);
}
@Test(dependsOnMethods= {"testserialization"})
public void testDeserialization()
{
Response response=given()	
.pathParam("id", studentID)
.when()
.get("http://localhost:3000/students/{id}")
.then()
.statusCode(200)
.extract().response();
//Deserilaztion starts(convert json to student object
student_serialization st = response.as(student_serialization.class);
System.out.println("Student details "+st);

assertThat(st.getName(),is("John"));
assertThat(st.getAge(),is("Hyderabad"));


assertThat(st.getName(),equalTo("John"));
}

}
