package pageObjectsLogBox;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import frameWork.BasePageFrameWork;
public class PageObjectsBrochurePage extends BasePageFrameWork {
	// Method:Select Practice on Brochure Page
	public void selectPracticeAndClickLoginButton() throws InterruptedException {
		driver.wait(200);
		driver.findElement(By.cssSelector(".navbar-nav [href='\\#\\/practice']")).click();
		driver.findElement(By.linkText("LOGIN")).click();
	}
	
	//Method:  Click Login Button
	public void clickLoginButtonToSubmitUsernameAndPassword() {
		driver.findElement(By.cssSelector("#login-submit")).click();
		
	}
	// Method: To Test if Values are Read from Excel File with Username and Password
	//@Test(dataProvider = "logBoxUserAccount", dataProviderClass = ReadDataFromExcel.class)
	public void testExcelReader(String username, String password) {
		System.out.println(username + " " + password);
	}
	// Method: To Login with Username and Password parameters
	public void loginUsernamePassword(String username, String password) throws IOException {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
	}
	
	
}