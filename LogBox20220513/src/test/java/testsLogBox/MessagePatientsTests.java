package testsLogBox;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import frameWork.MailDevAPIMethods;
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
	MailDevAPIMethods mailDevAPIMethods = new MailDevAPIMethods();

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
	public void shouldDisplayMessageQuestionnaireFormAppointmentOptions() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("lbautomationpreadmissiontodischarge@gmail.com", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		// THEN
		Boolean messageOption = pageObjectsMessagePatientPage.isMessageOptionDisplaying();
		Boolean questionnaireOption = pageObjectsMessagePatientPage.isQuestionnaireOptionDisplaying();
		Boolean formsOption = pageObjectsMessagePatientPage.isFormOptionDisplaying();
		Boolean appointmentOption = pageObjectsMessagePatientPage.isAppointmentDisplaying();

		Assert.assertTrue(messageOption);
		Assert.assertTrue(questionnaireOption);
		Assert.assertTrue(formsOption);
		Assert.assertTrue(appointmentOption);
		Reporter.log("The options for message, questionnaire, form and appointment reminder display.");
	}

	@Test
	public void shouldSMSPatientMessageText() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String message = "This is a test message";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("lbautomationpreadmissiontodischarge@gmail.com", "LogBoxMaster");
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

	@Test
	public void shouldSMSPatientQuestionnaire() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("lbautomationpreadmissiontodischarge@gmail.com", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnQuestionnaireExpandButton();
		pageObjectsMessagePatientPage.selectQuestionnaire();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The questionnaire was sent to the patient successfully");
	}

	@Test
	public void shouldSMSPatientForm() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("lbautomationpreadmissiontodischarge@gmail.com", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnFormsExpandButton();
		pageObjectsMessagePatientPage.selectForm();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The form was sent to the patient successfully");
	}

	@Test
	public void shouldSMSPatientAppointmentReminder() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";
		String localDatePlus10;
		String localTimePlusTwoMinutes;
		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("lbautomationpreadmissiontodischarge@gmail.com", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();

		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnAppointmentReminderExpandButton();
		localDatePlus10 = getDateWithAddSubstractDays(10);
		localTimePlusTwoMinutes = getTimeWithAddSubstractMinutesUsedByStartTime(2);
		basePageLogBox.enterDate(localDatePlus10);
		basePageLogBox.enterTime(localTimePlusTwoMinutes);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The appointment reminder was sent to the patient successfully");
	}

	public void shouldEmailPatientMessage() {

	}

	public void shouldEmailPatientQuestionnaire() {

	}

	public void shouldEmailPatientForm() {

	}

	public void shouldEmailPatientAppointmentReminder() {

	}

	@Test
	public void shouldReceiveSmsByPatient() throws InterruptedException, IOException {
		String patientName = "Charlie TestPatient";
		String message = "This is a test sms message";
		String localDate;

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginUsernamePassword("logboxtest", "LogBoxMaster");
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		mailDevAPIMethods.deleteAllMailsInMailbox();
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnSmsExpandIcon();
		localDate = getLocalDateTime();
		pageObjectsMessagePatientPage.enterTextInSmsTextArea(message + localDate);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		Thread.sleep(100);
		// THEN
		mailDevAPIMethods.verifyTextOfMailReceived(message + localDate + " Do Not Reply.");
		Reporter.log("The sms message was received by the patient in mailbox.");

	}
}
