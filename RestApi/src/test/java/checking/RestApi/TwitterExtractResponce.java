package checking.RestApi;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TwitterExtractResponce {
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
	public void f() {
		Response res=
	given()
		.auth()
		.oauth(consumerkey, consumersecret, token, tokenSecret)
		.queryParam("status", "my second tweet5")
	.when()
		.post("/update.json")
	.then()
		.statusCode(200)
		.extract().response();
		
		String id=res.path("id_str");
		System.out.println(id);
		
		/**
		 * another way to find the response
		 * */
		String resPathString=res.asString();
		
		//System.out.println(res.body().prettyPrint());
		JsonPath jp=new JsonPath(resPathString);
		String Username=jp.get("user.name");
		System.out.println(Username);
	}
}
