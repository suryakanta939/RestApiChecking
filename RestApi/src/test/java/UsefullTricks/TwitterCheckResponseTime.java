package UsefullTricks;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;



public class TwitterCheckResponseTime {
	/**
	 * Given i have this information
	 * When i perform this operation
	 * Then this should be the out put
	 * 
	 * */
	String consumerKey="sWN0VxkPyhLhM4QAqrQFwBvNG";
	String conumserSecret="f6FXkm48kMtiPCMzI3CFszfPIOp1nG61RdcEjLz9udlMFYLrC8";
	String token="2890493136-9bH8pqH4GFfoouzsSjvGPVLopYtu7QArKCRdRRA";
	String tokenSecret="yAJs2cG7e0LqeWQuyjZUSCpilP8fwgh6EFaMQShuoWdh3";
	
	@BeforeClass
	public void seetingUp(){
		//src url="https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=hi surya"
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
		
	}
  @Test
  public void checkResponseTime() {
	 long responseTime=
	  given()
	     .log()
	     .all()
	     .auth()
	     .oauth(consumerKey, conumserSecret, token, tokenSecret)
	     .queryParam("screen_name", "hi surya")
	  .when()
	    .get("/user_timeline.json")
	    // here we are doing the validation as response time and its in when method
	  .time();
	   // .timeIn(TimeUnit.SECONDS);
	 System.out.println("response time is "+responseTime);

  }
  @Test
  public void anotherwayToChck(){
			given()
			     .log()
			     .all()
			     .auth()
			     .oauth(consumerKey, conumserSecret, token, tokenSecret)
			     .queryParam("screen_name", "hi surya")
			.when()
			    .get("/user_timeline.json")
			   
			.then()
			 .statusCode(200) 
			 // here we are doing the validation as response time and its in then method
			 .time(lessThan(1L),TimeUnit.SECONDS)
			  .log()
			  .body()
			  /**
			   * here if we need to validate multiple things then 
			   * instead of writing the duplicate things again and again
			   * we can user "rootPath"
			   * Ex.here user and entities are used multiple time
			   * 
			   * */
			  .rootPath("user")  //this root path is for name & screen name
			  
			  .body("name", hasItem("suryakanta sahoo"))
			  .body("screen_name", hasItem("East09Surya"))
			  
			  .rootPath("entities.hashtags[0]") // this root path is for the text and size
			  
			  .body("size()", equalTo(2))
			  .body("text", hasItem("testing1"));
			 
			 
			/**
			 * Note:- in before class also we can use the root path
			 *       but problem is we can use only one for all the method
			 *       
			 *     To see how we can use check it in before class   
			 * */  
  }
  
  
}
