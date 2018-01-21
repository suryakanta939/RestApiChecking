package checking.RestApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ExtractXmlResponse {
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
	        .get("distancematrix/xml")
	    .then()
	       .statusCode(200)
	     .extract().response();
//	 System.out.println(res.body().prettyPrint());
	  String rspBody=res.asString();
	  System.out.println(rspBody);
	//finding the value of distance  
	  String distance=res.path("distancematrixresponse.row.element.distance.value");
	  System.out.println("the distance is "+distance);
	  
	  /**
	   * another way to extract the response
	   * */
	  
	  XmlPath xml=new XmlPath(rspBody);
	  String data=xml.get("distancematrixresponse.row.element.distance.value");
	  System.out.println(data);
  }
}
