package Fileupload_download;

import java.io.File;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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

public class Upload_Download {
	@Test(enabled=false)
	void fileUpload()

	{
		File file = new File("C:\\Users\\Admin\\Desktop\\restapi_upload\\sample.pdf");

		given()
		    .multiPart("file", file)
		.when()
		    .post("http://localhost:3000/upload")
		.then()
		    .statusCode(200)
		    .log().all();
	}
	@Test(enabled=false)
	void fileDownload() throws IOException
	{
		byte[] bytes = 
				given()
				.when()
				    .get("http://localhost:3000/Download/1765343990945-sample.pdf")
				.then()
				    .statusCode(200)
				    .log().all()
				    .extract().asByteArray()
				    ;
					
				FileOutputStream fos = new FileOutputStream("downloaded.pdf");
				fos.write(bytes);
				fos.close();
				
	}
	@Test
	void fileUploadMultiple()
	{
		File file1 = new File("C:\\Users\\Admin\\Desktop\\restapi_upload\\sample.pdf");
        File file2 = new File("C:\\Users\\Admin\\Desktop\\restapi_upload\\sample - Copy.pdf");

        given()
            .multiPart("files", file1)
            .multiPart("files", file2)
        .when()
            .post("http://localhost:3000/upload")
        .then()
            .statusCode(200)
            .log().all();
	}
}
