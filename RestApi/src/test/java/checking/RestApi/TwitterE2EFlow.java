package checking.RestApi;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TwitterE2EFlow {
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
	String tweetId;
	@BeforeClass
	public void setUp(){
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
	}
	@Test()
	public void postTweet() {
		Response res=
	given()
		.auth()
		.oauth(consumerkey, consumersecret, token, tokenSecret)
		.queryParam("status", "my tweet on sunday")
	.when()
		.post("/update.json")
	.then()
		.statusCode(200)
		.extract().response();
		
		 tweetId=res.path("id_str");
		System.out.println("The id of the tweet is "+tweetId);
		
		/**
		 * another way to find the response
		 * */
		String resPathString=res.asString();
		
		//System.out.println(res.body().prettyPrint());
		JsonPath jp=new JsonPath(resPathString);
		String Username=jp.get("user.name");
		System.out.println(Username);
	}
	
	@Test(dependsOnMethods="postTweet")
	public void readTweet(){
		Response res=
				given()
					.auth()
					.oauth(consumerkey, consumersecret, token, tokenSecret)
					.queryParam("id",tweetId)
				.when()
					.get("/show.json")
				.then()
					.statusCode(200)
					.extract().response();
		
	//	System.out.println(res.body().prettyPrint());
		
					String screnName=res.path("user.screen_name");
					String tweetText=res.path("text");
					System.out.println("the tweetScreen is "+screnName);
					System.out.println("the tweet is "+tweetText);
	}
	@Test(dependsOnMethods="readTweet",enabled=false)
	public void deleteTweet(){
		Response res=
				given()
					.auth()
					.oauth(consumerkey, consumersecret, token, tokenSecret)
					/**
					 * if we are using the below line then we can use the 3rd option directly
					 * else the other two we can use
					 * */
					.queryParam("id",tweetId)
				.when()
				/*here we can use the delete in three different way*/
					.post("/destroy/"+tweetId+".json")
				//	.post("/destroy/954942604315000832.json")
					//post("/destroy.json")
				.then()
					.statusCode(200)
					.extract().response();
					
					String screnName=res.path("user.screen_name");
					String tweetText=res.path("text");
					System.out.println("the tweetScreen is "+screnName);
					System.out.println("the tweet is "+tweetText);
	}
	
	/**
	 * this is the perfect way when there will be no query param
	 * in url endpoint
	 * 
	 * Ex :https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json
	 * */
	
	@Test(dependsOnMethods="readTweet")
	public void deleteTweetAnotherWay(){
		Response res=
				given()
					.auth()
					.oauth(consumerkey, consumersecret, token, tokenSecret)
					/**
					 * if we are using the below line then we can use the 3rd option directly
					 * else the other two we can use
					 * */
					.pathParam("id",tweetId)
				.when()
				   .post("/destroy/{id}.json")
				.then()
					.statusCode(200)
					.extract().response();
					
					String screnName=res.path("user.screen_name");
					String tweetText=res.path("text");
					System.out.println("the tweetScreen is "+screnName);
					System.out.println("the tweet is "+tweetText);
	}
	
	
}
