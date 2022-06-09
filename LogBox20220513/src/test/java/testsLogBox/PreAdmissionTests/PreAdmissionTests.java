package testsLogBox.PreAdmissionTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import frameWork.BasePageFrameWork;
import frameWork.ReadDataFromExcel;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsPreAdmissionPage;

public class PreAdmissionTests extends BasePageFrameWork {
	// Instantiate Page Object Classes
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsPreAdmissionPage pageObjectsPreAdmissionPage = new PageObjectsPreAdmissionPage();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	
//	@AfterTest
//	public void cleanUpAfterTest() throws InterruptedException {
//		cleanUp();
//	}

	@Test
	public void shouldOpenPreAdmissionPageAfterPatientSearch() throws IOException, InterruptedException {
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsPreAdmissionPage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch("One");
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		//Assert
		String patientName = pageObjectsPreAdmissionPage.getTextFromPreAdmissionPatientHeader();
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("preAdmission"));
		Assert.assertTrue(patientName.contains("One"));
		Reporter.log("URL contains \"preAdmission\"");
		Reporter.log("A new PreAdmission page was opened for patient: PreAdmission One");
	}


	@Test
	public void shouldValidateRequiredFieldsWhenCreatingPreAdmission()
			throws IOException, InterruptedException {
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsPreAdmissionPage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch("One");
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		pageObjectsPreAdmissionPage.enterSpecialInstructionsToPatient();
		pageObjectsPreAdmissionPage.clickSaveButton();
	
		//Assert: Validation displays
		String validationError = pageObjectsPreAdmissionPage.getTextFromPreAdmissionRequiredFieldsValidationPageMessage();
		Assert.assertTrue(validationError.contains("fill in the form"));
		String pageValidation = pageObjectsPreAdmissionPage.getTextFromPreAdmissionRequiredFieldsValidationErrorText();
		Assert.assertEquals(pageValidation, "The Expected Date of Admission is required.");
		Reporter.log("The Preadmission is validated on required fields");

	}
	@Test
	public void shouldCreatePreAdmissionOnCurrentDateTimeFromPracticeQuickLinkWithRequiredFieldsOnly(String patientName)
			throws IOException, InterruptedException {
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsPreAdmissionPage.clickOnPreAdmissionButtonInLeftMenu();
		//Get a list of the number of preadmissions to verify later
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch("One");
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		//pageObjectsPreAdmissionPage.enterRequiredFieldsPreadmission();
		pageObjectsPreAdmissionPage.clickSaveButton();
	
		//String preadmissionListValuePatientName = pageObjectsPreAdmissionPage.getTextFromPreAdmissionList();
		//Assert.assertEquals(preadmissionListValuePatientName, "Preadmission One");
		//String preadmissionListValueHospitalName = pageObjectsPreAdmissionPage.getTextFromPreAdmissionList();
				//Assert.assertEquals(preadmissionListValueHospitalName, "Preadmission One");
		//Get a list of the number of preadmissions, then verify that the page contains initial plus one		
		Reporter.log("The Preadmission with required fields only was created successfully");


	}

	@Test
	public void shouldCreatePreAdmissionOnCurrentDateTimeFromPatientMenuWithAllFields(String patientName)
			throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		// Call method to select preadmission menu item in left pane (from baselogbox)

		// Method to click on create preadmission button

		// Method to add patient name

		// Method to complete all fields - use a constructor here (possibly use excel
		// for constructor)

		// Verify the preadmission is created with a list

	}

	@Test
	public void shouldSendPreAdmissionToPatientEmailToComplete(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		// Call method to select preadmission menu item in left pane (from baselogbox)

		// Method to click on create preadmission button

		// Method to add patient name

		// Method to add a new preadmission (possibly use API here)

		// MEthod to email the preadmission to patient

		// Method to login to mailbox

		// Verify the email success message displays
		// Verify the email is received in the mailbox

	}

	@Test
	public void shouldViewPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		// Call method to select preadmission menu item in left pane (from baselogbox)

		// Method to click on the preadmission

		// Verify preadmission displays with all required info

	}

	@Test
	public void shouldEditPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		// Call method to select preadmission menu item in left pane (from baselogbox)

		// Method to click on the preadmission

		// Method to edit the preadmission

		// MEthod to save

		// Verify the save success message displays

		// Method to open the preadmission again

		// Verify the changed details display
	}

	@Test
	public void shouldPrintPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		// Call method to select preadmission menu item in left pane (from baselogbox)

		// Method to click on the print icon in preadmission list

		// Method to click print in print dialog

		// Verify the PDF is in the system (add target for the saved file in the
		// project)

	}

	@Test
	public void shouldCompletePreAdmissionViaKiosk(String patientName) throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		// Call method to select preadmission menu item in left pane (from baselogbox)

		// Method to click on the print icon in preadmission list

		// Method to click print in print dialog

		// Verify the PDF is in the system (add target for the saved file in the
		// project)

	}

	@Test
	public void shouldDisplayNotificationInPracticeAfterCompletionOfPreAdmissionByPatient(String patientName)
			throws IOException {
		pageObjectsBrochurePage.loginUsernamePassword("lbPreAdmissionToDischarges", "LogBoxMaster");
		System.out.println("Logged in successfully using valid username and valid password: "
				+ "lbPreAdmissionToDischarges" + "," + "LogBoxMaster");
		// Call method to select preadmission menu item in left pane (from baselogbox)

		// Method to click on the print icon in preadmission list

		// Method to click print in print dialog

		// Verify the PDF is in the system (add target for the saved file in the
		// project)

	}

}
