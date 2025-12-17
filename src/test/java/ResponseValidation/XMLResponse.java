package ResponseValidation;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.path.xml.XmlPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.List;
public class XMLResponse {
@Test
void testXMLResponse()
{
	given()
	
	.when()
	.get("https://mocktarget.apigee.net/xml")
	.then()
	.statusCode(200)
	.contentType("application/xml")
	.log().body()
	.body("root.city",equalTo("San Jose"));//. is represetig child node 
	
}
@Test
void testXMLResponse_attributes()
{
	given()
	
	.when()
	.get("https://httpbin.org/xml")
	.then()
	.statusCode(200)
	.contentType("application/xml")
	.log().body()
	.body("slideshow.@tiltle", equalTo("Sample Slide Show"));//@represents attributes
	;
	
}
@Test
void testXMLResponse_XMPparsing()
{
	Response response=given()
	
	.when()
	.get("https://httpbin.org/xml")
	.then()
	.statusCode(200)
	.contentType("application/xml")
	.extract().response();
	
	XmlPath xmlPath = new XmlPath(response.asString());
			
			//Extract number of slides in the response
			
			List<String> slideTitles= xmlPath.getList("slideshow.slide.title")	;	
			//count title
			
			assertThat(slideTitles,is(2));
			//validate slide titles
			
			assertThat(slideTitles.get(0),is("Wake up to WonderWidgets"));
			assertThat(slideTitles.get(1),is("Overview"));
			
	
}
}
