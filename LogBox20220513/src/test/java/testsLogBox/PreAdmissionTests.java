package testsLogBox;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import frameWork.BasePageFrameWork;
import frameWork.ReadDataFromExcel;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsBrochurePage;

public class PreAdmissionTests extends BasePageFrameWork {
	// Instantiate Page Object Classes
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	String patientName = "PreAdmissionOne Patient";//Would like to ideally read this form excel file
	String username = "lbPreAdmissionToDischarges";//Would like to ideally read this form excel file
	String password = "LogBoxMaster";//Would like to ideally read this form excel file

	@Test
	public void shouldSearchForPatientOnPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword(username,password );
		System.out.println("Logged in successfully using valid username and valid password: "
				+ username + "," + password);
		//Call method to select preadmission menu item in left pane (from baselogbox)
		driver.findElement(By.cssSelector(".elevation-0.theme--light.transparent.v-toolbar  div[role='list'] > div[role='listitem'] .material-icons.theme--light.v-icon")).click();
		driver.findElement(By.cssSelector("[data-cy='lb-practice-nav-drawer-preadmissions'] .v-list__tile__title")).click();
		//Find search box and enter patient name in the searchbox
		driver.findElement(By.cssSelector("searchbox css")).sendKeys(patientName);
		//Verify the patient list that is returned
		//WebElement <List> = driver.findEle
				//actualNumberRows get number of rows
				//get value of first row
		//Assert.assertEqual(actualNumberRows,expectedNumberRows );
		System.out.println("The expected patient list is returned containing patient : "+ patientName);
		//Report log info
		Reporter.log("TestName: shouldSearchForPatientOnPreAdmissionScreen, TestResults: ");
		//Reporter.log("Expected Number of Rows " + " " + expectedNumberRows);
		//Reporter.log("Actual Number of Rows " + actualNumberRows);
		Reporter.log("Patient Name is returned: " + patientName);
	}
	
	@Test
	public void shouldOpenPreAdmissionDialogFromPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on create preadmission button
		
		//Method to add patient name
		
		//Verify preadmission dialog is opened

	}
	
	@Test
	public void shouldDisplayAllRequiredFieldsOnPreAdmissionDialog(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on create preadmission button
		
		//Method to add patient name
		
		//Verify all required fields display

	}
	
	@Test
	public void shouldDisplayAllNonRequiredFieldsOnPreAdmissionDialog(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on create preadmission button
		
		//Method to add patient name
		
		//Verify all non-required fields display

	}
	
	@Test
	public void shouldCreatePreAdmissionOnCurrentDateTimeFromPracticeQuickLinkWithRequiredFieldsOnly(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on create preadmission button
		
		//Method to add patient name
		
		//Method to complete all required fields - use a constructor here (possibly use excel for constructor)
		
		//Verify the preadmission is created with a list

	}
	
	@Test
	public void shouldCreatePreAdmissionOnCurrentDateTimeFromPatientMenuWithAllFields(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on create preadmission button
		
		//Method to add patient name
		
		//Method to complete all fields - use a constructor here (possibly use excel for constructor)
		
		//Verify the preadmission is created with a list

	}
	

	
	@Test
	public void shouldSendPreAdmissionToPatientEmailToComplete(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on create preadmission button
		
		//Method to add patient name
		
		//Method to add a new preadmission (possibly use API here)
		
		//MEthod to email the preadmission to patient
		
		//Method to login to mailbox
		
		//Verify the email success message displays
		//Verify the email is received in the mailbox

	}
	
	
	@Test
	public void shouldViewPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on the preadmission
		
		//Verify preadmission displays with all required info
		

	}
	
	@Test
	public void shouldEditPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on the preadmission
		
		
		//Method to edit the preadmission
		
		//MEthod to save
		
		//Verify the save success message displays
		
		//Method to open the preadmission again
		
		
		//Verify the changed details display
	}
	
	@Test
	public void shouldPrintPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on the print icon in preadmission list
		
		//Method to click print in print dialog
		
		//Verify the PDF is in the system (add target for the saved file in the project)
		
	
	}
	
	@Test
	public void shouldCompletePreAdmissionViaKiosk(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on the print icon in preadmission list
		
		//Method to click print in print dialog
		
		//Verify the PDF is in the system (add target for the saved file in the project)
		
	
	}
	
	@Test
	public void shouldDisplayNotificationInPracticeAfterCompletionOfPreAdmissionByPatient(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		//Call method to select preadmission menu item in left pane (from baselogbox)
		
		//Method to click on the print icon in preadmission list
		
		//Method to click print in print dialog
		
		//Verify the PDF is in the system (add target for the saved file in the project)
		
	
	}

}
