package checking.RestApi;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class ToolsQa {
	
 // @Test
  public void f() {
	  // http://restapi.demoqa.com/utilities/weather/city
	  RestAssured.baseURI = "http://restapi.demoqa.com";
	  RestAssured.basePath="/utilities";
	  
	  Response res=
 given()
	    .log()
	    .all()
	    .pathParam("city", "Hyderabad")
.when()
	  .get("/weather/{city}")
 .then()
	  .log()
	  .all()
	  .time(lessThan(5L), TimeUnit.SECONDS)
	  .statusCode(200)
	  .extract().response();
	  System.out.println(res.getBody().asString());
	  
  }
  /**
   *This test case is to send the json request from body
   * There is an another way to send requst from the url
   * in that case we have to use the querparam/path param in given()
   * */
  @Test
  public void postUser(){
	 // https://nexter-alexa-for-business.herokuapp.com/a4b/api/v1.0/add_new_user
	  RestAssured.baseURI="https://nexter-alexa-for-business.herokuapp.com";
	  RestAssured.basePath="/a4b/api/v1.0";
	 JSONObject requestParams = new JSONObject();
	  requestParams.put("Path", "/hello"); 
	  requestParams.put("UserName", "suriyaKishoore");
	 // File f=new File("C:\\Users\\User\\Desktop\\myfile.json");
given()
	  .log()
	  .all()
	  .body(requestParams.toString())
	  .contentType("application/json") 
.when()
      .post("/add_new_user")
 .then()
      .log()
      .all()
      .statusCode(200);
      
  }
}
