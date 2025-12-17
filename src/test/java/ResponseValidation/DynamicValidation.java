package ResponseValidation;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
public class DynamicValidation {
	@Test
	void testJsonResponseBody()
	{
		ResponseBody responseBody =given()
		.when()
		.get("http://localhost:3000/employees")
		.then()
		.statusCode(200)
		.extract().response().body();
		JsonPath jsonpath = new JsonPath(responseBody.asString());//this is from responsebody to jsonpath for jsonobject to json path we use tostring
		//get the size of json array
		int employeeCount= jsonpath.getInt("employees.size()");   //returns the nnumber of elements or length of the array
		
		//print all the details of the employee
		for(int i=0;i<employeeCount;i++)
		{
			String firstname = jsonpath.getString("["+i+"].first_name");
			String lastname = jsonpath.getString("["+i+"].last_name");
			String email = jsonpath.getString("["+i+"].email");
			String gender = jsonpath.getString("["+i+"].gender");
			
			System.out.println(firstname +" " + lastname+ " "+email +" "+gender);
		}
		
		boolean status =false;
		
		for(int k=0;k<employeeCount;k++)
		{
			String firstname_actual = jsonpath.getString("["+k+"].first_name");
			if(firstname_actual.equals("Ananya"))
					{
				status=true;
				break;
				
				
					}
		}
			
			assertThat(status,is(true));
			assertThat(employeeCount, greaterThan(0));
			int total =0;
			for(int l=0;l<employeeCount;l++)
			{
				int idvalue = jsonpath.getInt("["+l+"].id");
				total=total+idvalue;
				
			}
			
			System.out.println(total);
			
			
			
	}
}

