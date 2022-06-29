package testsLogBox;

import java.io.IOException;

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

	@AfterTest
	public void cleanUpAfterTest() {
		cleanUp();
	}

	@AfterMethod
	public void logOut() {
		basePageLogBox.logOutOfLogBox();
	}

	@Test
	public void searchPatientToMessage() throws InterruptedException, IOException {
		String patientName = "Charlie";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("logboxtest", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		// THEN
		// Assert that the patient name is returned

	}

	public void shouldNotAllowDraftPatientForMessages() {

	}

	public void shouldDisplaySmsEmailOptionsForMessages() {

	}

	public void shouldSMSPatientMessageText() {

	}

	public void shouldSMSPatientAppointmentReminder() {

	}
	
	public void shouldEmailPatientAppointmentReminder() {

	}
}
