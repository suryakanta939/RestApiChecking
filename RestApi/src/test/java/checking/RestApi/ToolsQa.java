package checking.RestApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class ToolsQa {
	
  @Test
  public void f() {
	  
	  RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	 // RestAssured.basePath="utilities/weather/city";
	  Response res=
	  given()
	  //.get("Hyderabad");
	 // .param("city")
	  .when()
	  .get("Hyderabad");
	  System.out.println(res.getBody().asString());
  }
}
