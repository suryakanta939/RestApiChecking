package logRequestAndResponse;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class LogResponse {
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
		 * if we need to log  the response then just need 
		 * to use the log after the then()
		 * then methods as per the need 
		 * like all()/body()/header()/parameters() etc
		 * 
		 * if we need to log if the validation fails
		 * then use ifValidationFails()/iferror()
		 * */
	given()
		.auth()
		.oauth(consumerkey, consumersecret, token, tokenSecret)
		.queryParam("status", "my second tweet2")
	.when()
		.post("/update.json")
	.then()
	.log()
	//.all()
	//.body()
	//.headers()
	.ifError()
		.statusCode(200);

	}
}
