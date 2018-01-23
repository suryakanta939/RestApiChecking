package AssertExample;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TwitterHardAssertExample {
	String consumerKey="sWN0VxkPyhLhM4QAqrQFwBvNG";
	String consumerSecret="f6FXkm48kMtiPCMzI3CFszfPIOp1nG61RdcEjLz9udlMFYLrC8";
	String token="2890493136-9bH8pqH4GFfoouzsSjvGPVLopYtu7QArKCRdRRA";
	String tokenSecret="yAJs2cG7e0LqeWQuyjZUSCpilP8fwgh6EFaMQShuoWdh3";
	@BeforeClass
	public void setUp(){
		
		// url=https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=hi surya
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
	}
	
  @Test
  public void testMethod() {
	  given()
	     .log()
	     .ifValidationFails()
	     .auth()
	     .oauth(consumerKey, consumerSecret, token, tokenSecret)
	    // .queryParam("screen_name", "hi surya")
	     .queryParam("user_id", "2890493136")
	    
	  .when()
	     .get("/user_timeline.json")
	  .then()
	  .log()
	  .all()
	  .statusCode(200)
	  .body("user.name",hasItem("suryakanta sahoo"))
	  .body("entities.hashtags[0].text",hasItem("testing2"))
	  .body("entities.hashtags[0].size()",equalTo(2));
	   
  }
}
