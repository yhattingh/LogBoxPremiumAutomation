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
	public void shouldNotLoginWithInValidUsernameValidPassword(String username, String password) throws IOException {

	}
}