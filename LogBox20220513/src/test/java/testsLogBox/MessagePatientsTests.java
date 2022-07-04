package testsLogBox;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import frameWork.ReadDataFromExcel;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsActivityPage;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsHomePage;
import pageObjectsLogBox.PageObjectsMessagePatientPage;
import pageObjectsLogBox.PageObjectsPreAdmissionPage;

public class MessagePatientsTests extends BasePageFrameWork {
	// Instantiate Page Object Classes
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsPreAdmissionPage pageObjectsPreAdmissionPage = new PageObjectsPreAdmissionPage();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsActivityPage pageObjectsActivityPage = new PageObjectsActivityPage();
	PageObjectsMessagePatientPage pageObjectsMessagePatientPage = new PageObjectsMessagePatientPage();

//	@AfterTest
//	public void cleanUpAfterTest() {
//		cleanUp();
//	}
//
//	@AfterMethod
//	public void logOut() {
//		basePageLogBox.logOutOfLogBox();
//	}

	@Test
	public void searchPatientToMessage() throws InterruptedException, IOException {
		String patientName = "Charlie TestPatient";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("logboxtest", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		// THEN
		String patientTextInList = pageObjectsMessagePatientPage.getTextFromMessagePatientSearchList();
		Assert.assertEquals(patientTextInList, patientName);
		Reporter.log("The searched patient is returned in the list");

	}

	@Test
	public void shouldNotAllowDraftPatientForMessages() throws InterruptedException, IOException {
		String patientName = "Draft Patient";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("logboxtest", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		// THEN
		String errorTextInList = pageObjectsMessagePatientPage.getTextFromMessageDraftPatientError();
		Assert.assertEquals(errorTextInList, "No Results found");
		Reporter.log("The draft patients are excluded from the search for messaging patients");
	}

	@Test
	public void shouldSMSPatientMessageText() throws InterruptedException, IOException {
		String patientName = "Charlie TestPatient";
		String message = "This is a test message";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("logboxtest", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnSmsExpandIcon();
		pageObjectsMessagePatientPage.enterTextInSmsTextArea(message);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The message was sent to the patient successfully");

	}
	
	public void shouldReceiveSmsByPatient() throws InterruptedException, IOException {
		String patientName = "Charlie TestPatient";
		String message = "This is a test message";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("logboxtest", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnSmsExpandIcon();
		pageObjectsMessagePatientPage.enterTextInSmsTextArea(message);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		// THEN
		//Use maildev api here
		Reporter.log("The sms message was received by the patient");

		
	}

	public void shouldSMSPatientAppointmentReminder() {

	}

	public void shouldEmailPatientAppointmentReminder() {

	}
}
