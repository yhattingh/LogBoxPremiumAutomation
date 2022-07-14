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

	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsPreAdmissionPage pageObjectsPreAdmissionPage = new PageObjectsPreAdmissionPage();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsActivityPage pageObjectsActivityPage = new PageObjectsActivityPage();
	PageObjectsMessagePatientPage pageObjectsMessagePatientPage = new PageObjectsMessagePatientPage();
	MailDevAPIMethods mailDevAPIMethods = new MailDevAPIMethods();

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
		String patientName = "PreAdmission One";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The message sms was sent to the patient successfully");
	}

	@Test
	public void shouldSMSPatientQuestionnaire() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The questionnaire sms was sent to the patient successfully");
	}

	@Test
	public void shouldSMSPatientForm() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The form sms was sent to the patient successfully");
	}

	@Test
	public void shouldSMSPatientAppointmentReminder() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";
		String localDatePlus10;
		String localTimePlusTwoMinutes;
		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The appointment reminder sms was sent to the patient successfully");
	}

	@Test
	public void shouldEmailPatientMessage() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String message = "This is a test message";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnEmailRadioButton();
		pageObjectsMessagePatientPage.clickOnSmsExpandIcon();
		pageObjectsMessagePatientPage.enterTextInSmsTextArea(message);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The message sms was sent to the patient successfully");

	}

	@Test(enabled =false)
	public void shouldEmailPatientQuestionnaire() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnEmailRadioButton();
		pageObjectsMessagePatientPage.clickOnQuestionnaireExpandButton();
		pageObjectsMessagePatientPage.selectQuestionnaire();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The questionnaire email was sent to the patient successfully");
	}

	@Test
	public void shouldEmailPatientForm() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnEmailRadioButton();
		pageObjectsMessagePatientPage.clickOnFormsExpandButton();
		pageObjectsMessagePatientPage.selectForm();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The form email was sent to the patient successfully");
	}

	@Test
	public void shouldEmailPatientAppointmentReminder() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String expectedSuccessMessage = "Success";
		String localDatePlus10;
		String localTimePlusTwoMinutes;

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnEmailRadioButton();
		pageObjectsMessagePatientPage.clickOnAppointmentReminderExpandButton();
		localDatePlus10 = getDateWithAddSubstractDays(10);
		localTimePlusTwoMinutes = getTimeWithAddSubstractMinutesUsedByStartTime(2);
		basePageLogBox.enterDate(localDatePlus10);
		basePageLogBox.enterTime(localTimePlusTwoMinutes);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		// THEN
		String successMessage = pageObjectsMessagePatientPage.getSentMessageSuccess();
		Assert.assertEquals(successMessage, expectedSuccessMessage);
		Reporter.log("The appointment reminder email was sent to the patient successfully");
	}

	@Test
	public void shouldReceiveSmsTextByPatient() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String message = "This is a test sms message";
		String localDate;

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
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
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		Thread.sleep(100);
		// THEN
		mailDevAPIMethods.verifyTextOfMailReceived(message + localDate + " Do Not Reply.");
		Reporter.log("The sms message was received by the patient in mailbox.");
	}

	@Test(enabled = false)
	public void shouldReceiveSmsQuestionnaireByPatient() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String message = "Please complete the following questionnaire from LBAutomation PreadmissionToDischarges.";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		mailDevAPIMethods.deleteAllMailsInMailbox();
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnQuestionnaireExpandButton();
		pageObjectsMessagePatientPage.selectQuestionnaire();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		Thread.sleep(100);
		// THEN
		mailDevAPIMethods.verifyTextOfMailReceivedContains(message);
		Reporter.log("The sms questoinnaire was received by the patient in mailbox.");
	}

	@Test(enabled = false) 
	public void shouldReceiveSmsFormByPatient() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String message = "LBAutomation PreadmissionToDischarges has requested that you complete the following patient form: https://qa.logbox.co.za/survey";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		mailDevAPIMethods.deleteAllMailsInMailbox();
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnFormsExpandButton();
		pageObjectsMessagePatientPage.selectForm();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		Thread.sleep(100);
		// THEN
		mailDevAPIMethods.verifyTextOfMailReceivedContains(message);
		Reporter.log("The sms form was received by the patient in mailbox.");
	}

	@Test(enabled=false)
	public void shouldReceiveSmsAppointmentReminderByPatient() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String message = "Please remember your appointment with LBAutomation PreadmissionToDischarges on ";
		String messageDate;
		String message2 = " at ";
		String messageTime;
		String message3 = ". Please phone the practice directly if you cannot attend on 0120101001. Do Not Reply.";
		String localDatePlusTwo;
		String localTimePlusTwoMinutes;
		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		mailDevAPIMethods.deleteAllMailsInMailbox();
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnSmsRadioButton();
		pageObjectsMessagePatientPage.clickOnAppointmentReminderExpandButton();
		localDatePlusTwo = getDateWithAddSubstractDays(2);
		localTimePlusTwoMinutes = getTimeWithAddSubstractMinutesUsedByStartTime(2);
		basePageLogBox.enterDate(localDatePlusTwo);
		basePageLogBox.enterTime(localTimePlusTwoMinutes);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		Thread.sleep(100);
		// THEN
		messageDate = getDateInWordFormat(2);
		mailDevAPIMethods.verifyTextOfMailReceivedContains(
				message + messageDate + message2 + localTimePlusTwoMinutes + message3);
		Reporter.log("The sms form was received by the patient in mailbox.");
	}

	@Test (enabled = false)
	public void shouldReceiveEmailMessageByPatient() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String messageTextSubject = "Message";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		mailDevAPIMethods.deleteAllMailsInMailbox();
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnEmailRadioButton();
		pageObjectsMessagePatientPage.clickOnSmsExpandIcon();
		pageObjectsMessagePatientPage.enterTextInSmsTextArea(messageTextSubject);
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		Thread.sleep(100);
		// THEN
		mailDevAPIMethods.verifyHeaderTextOfMailReceived(messageTextSubject);
		Reporter.log("The email message was received by the patient in mailbox.");
	}

	@Test (enabled = false)
	public void shouldReceiveEmailQuestionnaireByPatient() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String messageTextSubject = "Practice Questionnaire";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		mailDevAPIMethods.deleteAllMailsInMailbox();
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnEmailRadioButton();
		pageObjectsMessagePatientPage.clickOnQuestionnaireExpandButton();
		pageObjectsMessagePatientPage.selectQuestionnaireForEmail();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		Thread.sleep(150);
		// THEN
		mailDevAPIMethods.verifyHeaderTextOfMailReceived(messageTextSubject);
		Reporter.log("The email with questionnaire was received by the patient in mailbox.");
	}

	@Test(enabled = false)
	public void shouldReceiveEmailFormByPatient() throws InterruptedException, IOException {
		String patientName = "PreAdmission One";
		String messageTextPatientName = "Preadmission One";
		String messageTextAction = "COMPLETE NOW";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.loginPreAdmission();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		mailDevAPIMethods.deleteAllMailsInMailbox();
		pageObjectsHomePage.clickOnMessagePatientButtonInLeftMenu();
		pageObjectsMessagePatientPage.clickSearchOnMessagePatientPage();
		pageObjectsMessagePatientPage.enterPatientNameInSearchFieldOnMessagePatientPage(patientName);
		pageObjectsMessagePatientPage.clickOnReturnedPatient();
		pageObjectsMessagePatientPage.clickOnEmailRadioButton();
		pageObjectsMessagePatientPage.clickOnFormsExpandButton();
		pageObjectsMessagePatientPage.selectForm();
		pageObjectsMessagePatientPage.clickSendMessageButton();
		pageObjectsMessagePatientPage.closeMessageSuccessToast();
		Thread.sleep(100);
		// THEN
		mailDevAPIMethods.verifyTextOfMailReceivedContains(messageTextPatientName);
		mailDevAPIMethods.verifyTextOfMailReceivedContains(messageTextAction);
		Reporter.log("The email with form was received by the patient in mailbox.");
	}

	@Test(enabled = false)
	public void shouldReceiveEmailAppointmentReminderByPatient() {

	}
}
