package testsGeoClock;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import frameWork.BasePageFrameWorkGeoClock;
import pageObjectsGeoClock.PageObjectsBrochurePageGC;
import pageObjectsGeoClock.BasePageGeoClock;


public class LoginManagerTests extends BasePageFrameWorkGeoClock {
	// Instantiate Page Object Classes
	BasePageGeoClock basePageGeoClock = new BasePageGeoClock();
	PageObjectsBrochurePageGC pageObjectsBrochurePageGC = new PageObjectsBrochurePageGC();

	@Test
	public void shouldLoginValidUsernameValidPasswordGeoClock() throws IOException, InterruptedException {
		pageObjectsBrochurePageGC.clickPasswordLoginOption();
		pageObjectsBrochurePageGC.loginManager();
		pageObjectsBrochurePageGC.clickLoginButtonToSubmitUsernameAndPassword();
		Thread.sleep(100);
		waitForElement(15, By.xpath("//div[contains(text(),'account_circle')]"));
		String expectedUrl = getURL();
		Assert.assertEquals(expectedUrl, "https://labs-dev.geoclock.net/#/approvals");
		Reporter.log("Logged in with a valid manager username and valid password into Expected URL = " + expectedUrl);
	}

}