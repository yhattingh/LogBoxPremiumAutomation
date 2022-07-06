package testsLogBox;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import frameWork.BasePageFrameWork;
import frameWork.FileUtilities;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsBrochurePage;

public class LoginPracticeTests extends BasePageFrameWork {
	// Instantiate Page Object Classes
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	FileUtilities fileUtilities = new FileUtilities();

	@Test
	public void shouldLoginWithValidUsernameValidPassword() throws IOException, InterruptedException {
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertUsernameAndPasswordFromExcel(1, 1, 2);
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		waitForElement(5, By.cssSelector(".v-toolbar__items > a:nth-of-type(1) > .v-btn__content"));
		String expectedUrl = getURL();
		Assert.assertEquals(expectedUrl, "https://qa.logbox.co.za/premium/#/");
		Reporter.log("Logged in with a valid username and valid password into Expected URL = " + expectedUrl);
	}

	@Test
	public void shouldNotLoginWithInValidUsernameValidPassword() throws IOException, InterruptedException {
		
		String username = "NotValidUsername";
		String password = "LogBoxMaster";
		String expectedErrorText = "Invalid username and/or password supplied. Please try again.";
		
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword(username, password);
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		
		String actualValidationErrorText = pageObjectsBrochurePage.validationErrorText();
		Assert.assertEquals(actualValidationErrorText, expectedErrorText);
		Reporter.log("Validation error prevents user from logging in with invalid username and valid password");
	}
	
	@Test
	public void shouldNotLoginWithValidUsernameInvalidPassword() throws IOException, InterruptedException {
		
		String username = "logboxtest";
		String password = "InvalidPassword";
		String expectedErrorText = "Invalid username and/or password supplied. Please try again.";
		
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword(username, password);
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		
		String actualValidationErrorText = pageObjectsBrochurePage.validationErrorText();
		Assert.assertEquals(actualValidationErrorText, expectedErrorText);
		Reporter.log("Validation error prevents user from logging in with invalid username and valid password");
	}
}