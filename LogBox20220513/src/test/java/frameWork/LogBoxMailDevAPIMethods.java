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

public class LogBoxMailDevAPIMethods extends BasePageFrameWork {

	
	public void setUp() {
		// specify the base url to the RESTFUL web service
		RestAssured.baseURI = "https://qa.logbox.co.za/maildev/email";
	}

	
	public void tearDown() {
		cleanUp();
	}

	
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
	

	public void deleteAllMailsInMailbox() {
		RestAssured.baseURI = "https://qa.logbox.co.za/maildev/email/all";
		given().auth().basic("dev", "1290zxnm").
		when().
		log().all().
		delete().
		then().
			statusCode(200);
		
	}

	public void verifyTextOfMailReceived(String messageText) {
		RestAssured.baseURI = "https://qa.logbox.co.za/maildev/email";
		String var = 
		given().auth().basic("dev", "1290zxnm").
		when().
		log().all().
		get().
		then().
			statusCode(200).
			and().
			body("[0].text", equalTo(messageText)).
			extract().path("[0].text");
			System.out.println("Value found:" + " " + var);
	}
	
	public void verifyTextOfMailReceivedContains(String messageText) {
		RestAssured.baseURI = "https://qa.logbox.co.za/maildev/email";
		String var = 
		given().auth().basic("dev", "1290zxnm").
		when().
		log().all().
		get().
		then().
			statusCode(200).
			and().body("[0].text", contains(messageText)).
			extract().path("[0].text");
			System.out.println("Value found:" + " " + var);
	}
	
	public void verifyHeaderTextOfMailReceived(String headerTextSubject ) {
		RestAssured.baseURI = "https://qa.logbox.co.za/maildev/email";
		String var = 
		given().auth().basic("dev", "1290zxnm").
		when().
		log().all().
		get().
		then().
		statusCode(200).
		and().
		body("[0].subject", equalTo(headerTextSubject)).
		extract().path("[0].subject");
		System.out.println("Value found:" + " " + var);
	}
}
