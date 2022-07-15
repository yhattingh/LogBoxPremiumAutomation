package pageObjectsLogBox;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import frameWork.BasePageFrameWork;
import frameWork.FileUtilities;

public class PageObjectsBrochurePage extends BasePageFrameWork {

	String inputDirectory = getDataConfigProperties("inputDir");
	String inputFile = inputDirectory + "logBoxUserAccounts.xlsx";

	// Method:Select Practice on Brochure Page
	public void selectPracticeAndClickLoginButton() throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='container']/nav//a[@href='#/login']")).click();
		waitForElement(100, By.cssSelector("[src='\\/img\\/Practice_Text\\.b772cae\\.png']"));
		driver.findElement(By.cssSelector("[src='\\/img\\/Practice_Text\\.b772cae\\.png']")).click();
	}

	// Method: Click Login Button
	public void clickLoginButtonToSubmitUsernameAndPassword() throws InterruptedException {
		driver.findElement(By.cssSelector("#login-submit")).click();
	}

	// Method: To Test if Values are Read from Excel File with Username and Password
	public void testExcelReader(String username, String password) {
		System.out.println(username + " " + password);
	}

	// Method: To Login with Username and Password parameters
	public void loginUsernamePassword(String username, String password) throws IOException {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
	}
	
	// Method: To Login with PreadmissionUsername and Password parameters
		public void loginPreAdmission() throws IOException {
			driver.findElement(By.id("username")).sendKeys("lbautomationpreadmissiontodischarge@gmail.com");
			driver.findElement(By.id("password")).sendKeys("LogBoxMaster");
		}

	// Method: To input Username and Password from Excel
	public void insertUsernameAndPasswordFromExcel(int rowNumber, int cellNumber1, int cellNumber2)
			throws IOException, InterruptedException {

		FileUtilities fileUtilities = new FileUtilities();

		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));

		fileUtilities.setExcelFile(inputFile, "sheet1");
		int i = rowNumber;
		username.sendKeys(fileUtilities.getCellData(i, cellNumber1));
		password.sendKeys(fileUtilities.getCellData(i, cellNumber2));
	}

	// Method: To log in as Activity Feed role
	public void insertActivityUsernameAndPasswordFromExcel() throws IOException, InterruptedException {

		FileUtilities fileUtilities = new FileUtilities();

		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));

		// Method: To call the FileUtilities method to read from Excel
		fileUtilities.setExcelFile(inputFile, "sheet1");
		int i = 1;
		username.sendKeys(fileUtilities.getCellData(1, 1));
		password.sendKeys(fileUtilities.getCellData(1, 2));
	}

	// Method: To log in as Preadmission role
	public void insertPreAdmissionUsernameAndPasswordFromExcel() throws IOException, InterruptedException {

		FileUtilities fileUtilities = new FileUtilities();

		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));

		// Method: To call the FileUtilities method to read from Excel
		fileUtilities.setExcelFile(inputFile, "sheet1");
		int i = 2;
		username.sendKeys(fileUtilities.getCellData(2, 1));
		password.sendKeys(fileUtilities.getCellData(2, 2));
	}
	
	//Method: To get the validation text on invalid login
	public String validationErrorText() {
	String validationErrorText = getElementText(By.cssSelector(".alert.alert-danger > div"));
	return validationErrorText;
	}
	
}
