package checking.RestApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ValidteJsonResponse {
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
	  given()
	        .param("units", "imperial")
	        .param("origins", "Washington,DC")
	        .param("destinations", "New+York+City,NY")
	        .param("key", "AIzaSyBfJMIOow9GW9rT1T-Cke2ipD1m3GA-kFM")
	   .when()
	        .get("distancematrix/json")
	    .then()
	       .statusCode(200)
	       .and()
	       .body("rows[0].elements[0].distance.text", equalTo("225 mi"))
	       .contentType(ContentType.JSON);
  }
}
