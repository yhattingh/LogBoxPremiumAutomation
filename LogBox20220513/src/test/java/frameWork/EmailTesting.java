package frameWork;

import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class EmailTesting extends BasePageFrameWork {

	@BeforeTest
	public void setUp() {
		// specify the base url to the RESTFUL web service
		RestAssured.baseURI = "https://qa.logbox.co.za/maildev/email";
	}

	@AfterTest
	public void tearDown() {
		cleanUp();
	}

	@Test
	public void verifyID() {
		String var = 
		given().auth().basic("dev", "1290zxnm").
		when().
		log().all().
		get().
		then().
			statusCode(200).
			and().
			body("[1].id", equalTo("lP6gSLZF")).
			extract().path("[1].id");
			System.out.println("Value found:" + " " + var);
	}
	
	@Test
	public void verifyOTPNumber() {
		String var = 
		given().auth().basic("dev", "1290zxnm").
		when().
		log().all().
		get().
		then().
			statusCode(200).
			and().
			body("[1].text", equalTo("Please enter the OTP number 11964 in order to link the application to your account.")).
			extract().path("[1].text");
			System.out.println("Value found:" + " " + var);
	}
	
	@Test
	public void verifyText() {
		String var = 
		given().auth().basic("dev", "1290zxnm").
		when().
		log().all().
		get().
		then().
			statusCode(200).
			and().
			body("[0].text", equalTo("Test Message Do Not Reply.")).
			extract().path("[0].text");
			System.out.println("Value found:" + " " + var);
	}
}
