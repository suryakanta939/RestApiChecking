package checking.RestApi;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ExtractJsonResponse {
	/**
	 * Given i have this information
	 * When i perform this operation
	 * Then this should be the out put
	 * 
	 * */
	@BeforeClass
	public void settingUp(){
		RestAssured.baseURI="https://maps.googleapis.com";
		RestAssured.basePath="/maps/api";
	}
	
  @Test()
  public void checkStatus() {
	  Response res=
	  given()
	        .param("units", "imperial")
	        .param("origins", "Washington,DC")
	        .param("destinations", "New+York+City,NY")
	        .param("key", "AIzaSyBfJMIOow9GW9rT1T-Cke2ipD1m3GA-kFM")
	   .when()
	        .get("distancematrix/json")
	    .then()
	       .statusCode(200)
	     .extract().response();
//	 System.out.println(res.body().prettyPrint());
	  String rspBody=res.asString();
	  System.out.println(rspBody);
	//finding the value of distance  
	  String duration=res.path("rows[0].elements[0].duration.text");
	  System.out.println("the distance is "+duration);
	  
	 /**
	  *Another way to retrive the data 
	  * 
	  * */ 
	  JsonPath jsp=new JsonPath(rspBody);
	  String text=jsp.get("rows[0].elements[0].duration.text");
	  System.out.println(text);
  }
}
