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
	String inputDirectory = getDataConfigProperties("inputDir");
	String inputFile = "logBoxUserAccounts.xlsx";
	@BeforeTest
	public void setUp() {
		//Clear db of preadmissions/admissions/discharges?
	}
	@Test
	public void shouldLoginWithValidUsernameValidPassword() throws IOException, InterruptedException {
		
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		WebElement username=driver.findElement(By.id("username"));
        WebElement password=driver.findElement(By.id("password"));
		//calling the FileUtilities class method to initialise the workbook and sheet
        fileUtilities.setExcelFile(inputFile,"sheet1");
		for(int i=1;i<=fileUtilities.getRowCountInSheet();i++)
	        {
			username.sendKeys(fileUtilities.getCellData(i,1));
        	password.sendKeys(fileUtilities.getCellData(i,2));
        	
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
			
		String actualUrl = getURL();
		String expectedUrl = "https://qa.logbox.co.za/premium/#/";
		Assert.assertEquals(actualUrl,expectedUrl );
		System.out.println("Logged in successfully using valid username and valid password: " + username + "," + password);
		Reporter.log("Expected URL " + " " + expectedUrl);
		Reporter.log("Actual URL is " + actualUrl);
	        }
	}
	
//	@Test
//	public void shouldNotLoginWithInValidUsernameValidPassword(String username, String password) throws IOException {
//		//readDataFromExcel.getDataFromExcel("logBoxUserAccount.xlsx", "sheet1");
//		pageObjectsBrochurePage.loginUsernamePassword(username, password);
//		boolean validationErrorDisplays = driver.equals("validation error exact text");
//		Assert.assertTrue(validationErrorDisplays, "invalid username or password");
//		System.out.println("Validation error displays to user when trying to log in with invalid credentials " + username + "," + password);
//		Reporter.log("Expected Validation Error Displays: " + validationErrorDisplays);
//	}
}