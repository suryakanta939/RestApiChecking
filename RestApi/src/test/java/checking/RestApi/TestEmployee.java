package checking.RestApi;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestEmployee {
	String user_id="";
	@BeforeClass
	public void setUp(){
		RestAssured.baseURI="http://dummy.restapiexample.com";
		RestAssured.basePath="/api/v1";
	}
	@Test(enabled=false)
	public void createEmployee(){
//		http://dummy.restapiexample.com/api/v1/create
		File myfile=new File("C:\\Users\\User\\Desktop\\jsonFile.json");
		Response rse=
				given()
					.body(myfile)
					.with()
					.contentType("application/json")
					.log()
					.ifValidationFails()
				.when()
					.post("/create")
				.then()
				.statusCode(200)
				.extract()
				.response();
		System.out.println(rse.asString());
		user_id=rse.jsonPath().getString("id");
		System.out.println(user_id);
		
		
	}
	@Test
	public void employeeDetails(){
//		http://dummy.restapiexample.com/api/v1/employee/{id}
		Response res=
		given()
			.log()
			.ifValidationFails()
			.pathParam("id",16257)
		.when()
			.get("/employee/{id}")
		.then()
			.statusCode(200)
			.extract()
			.response();
		System.out.println(res.asString());
	}
	
	@Test(enabled=false)
	public void updateEmployee(){
		
//		http://dummy.restapiexample.com/api/v1/update/{id}
		File myFile=new File("C:\\Users\\User\\Desktop\\update.json");
		Response res=
		given()
			.log().ifValidationFails()
			.body(myFile)
			.with()
			.contentType("application/json")
			.pathParam("id", 16257)
		.when()
			.put("/update/{id}")
		.then()
			.statusCode(200)
			.extract()
			.response();
		System.out.println(res.asString());
	}
}
