package ResponseValidation;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class parsingComplexJSON {
	
	
	JSONObject getJSONResponse() throws FileNotFoundException
	{
		File jsonFile = new File(".\\src\\test\\java\\Customer_info.json");
		FileReader fileReader = new FileReader(jsonFile);
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		JSONObject responseBody = new JSONObject(jsonTokener);
		return responseBody;
	}

	@Test
 void testUserDetailsValidation() throws FileNotFoundException
	{
		JsonPath jsonPath = new JsonPath(getJSONResponse().toString());//converting json object to json path
		
		String status = jsonPath.getString("status");
		assertThat(status,is("success"));//can directly access since status is independent
		
		//validate user details
		int id =jsonPath.getInt("data.userDetails.id");
		String  name  =jsonPath.getString("data.userDetails.name");
		assertThat(id,is(12345));//can directly access since status is independent
		assertThat(name,is("John Doe"));
		
		
		//validating phone numbers fileds, which is a json array
		
		String phoneNumber = jsonPath.getString("data.userDetails.phoneNumbers[0].number");
		String type = jsonPath.getString("data.userDetails.phoneNumbers[0].type");
		
		
		assertThat(type,is("home"));
		assertThat(phoneNumber,is("123-456-7890"));
		
		
		
		
		
		
		
		
		
		
				
}
}
		
