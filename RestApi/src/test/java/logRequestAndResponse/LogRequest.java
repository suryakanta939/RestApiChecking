package logRequestAndResponse;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LogRequest {
	/**
	 * Given i have this information
	 * When i perform this operation
	 * Then this should be the out put
	 * 
	 * */	
	
	
	String consumerkey="sWN0VxkPyhLhM4QAqrQFwBvNG";
	String consumersecret="f6FXkm48kMtiPCMzI3CFszfPIOp1nG61RdcEjLz9udlMFYLrC8";
	String token="2890493136-9bH8pqH4GFfoouzsSjvGPVLopYtu7QArKCRdRRA";
	String tokenSecret="yAJs2cG7e0LqeWQuyjZUSCpilP8fwgh6EFaMQShuoWdh3";

	@BeforeClass
	public void setUp(){
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
	}

	@Test
	public void testMethod() {
		/**
		 * if we need to log  the request then just need 
		 * to use the log after the given()
		 * then methods as per the need 
		 * like all()/body()/header()/parameters() etc
		 * 
		 * if we need to log if the validation fails
		 * then use ifValidationFails()
		 * */
	given()
	.log()
//	.all()
//	.body()
//	.headers()
//	.parameters()
	.ifValidationFails()
		.auth()
		.oauth(consumerkey, consumersecret, token, tokenSecret)
		.queryParam("status", "my second tweet1")
	.when()
		.post("/update.json")
	.then()
		.statusCode(200);

	}
}
