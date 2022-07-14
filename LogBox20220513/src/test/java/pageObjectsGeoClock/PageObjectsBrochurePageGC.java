package pageObjectsGeoClock;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import frameWork.BasePageFrameWork;
import frameWork.BasePageFrameWorkGeoClock;
import frameWork.FileUtilities;

public class PageObjectsBrochurePageGC extends BasePageFrameWorkGeoClock {

	String inputDirectory = getDataConfigProperties("inputDir");
	String inputFile = inputDirectory + "logBoxUserAccounts.xlsx";

	// Method:Select Practice on Brochure Page
	public void clickPasswordLoginOption() throws InterruptedException {
		clickElement(By.cssSelector(".col:nth-of-type(2) .login-option-icon"));
	}

	// Method: Click Login Button
	public void clickLoginButtonToSubmitUsernameAndPassword() throws InterruptedException {
		driver.findElement(By.cssSelector(".v-card__actions .v-btn__content")).click();
	}

	// Method: To Login with Username and Password parameters
	public void loginUsernamePassword(String username, String password) throws IOException {
		driver.findElement(By.cssSelector("[placeholder='Username']")).sendKeys(username);
		driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(password);
	}

	// Method: To Login with PreadmissionUsername and Password parameters
	public void loginManager() throws IOException {
		driver.findElement(By.cssSelector("[placeholder='Username']")).sendKeys("alopez");
		driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("option-CRUMP-whale");
	}

}
