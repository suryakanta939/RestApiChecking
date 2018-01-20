package checking.RestApi;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CheckingGet {
	
 //hello
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
	
  @Test(enabled=false)
  public void checkStatus() {
	  given()
	        .param("units", "imperial")
	        .param("origins", "Washington,DC")
	        .param("destinations", "New+York+City,NY")
	        .param("key", "AIzaSyBfJMIOow9GW9rT1T-Cke2ipD1m3GA-kFM")
	   .when()
	        .get("distancematrix/json")
	    .then()
	       .statusCode(200);   
  }
  
  @Test
  public void getresponse() {
	  Response res=
	  given()
	        .param("units", "imperial")
	        .param("origins", "Washington,DC")
	        .param("destinations", "New+York+City,NY")
	        .param("key", "AIzaSyBfJMIOow9GW9rT1T-Cke2ipD1m3GA-kFM")
	   .when()
	        .get("distancematrix/json");
	      System.out.println(res.body().prettyPrint());
  }
  
}
