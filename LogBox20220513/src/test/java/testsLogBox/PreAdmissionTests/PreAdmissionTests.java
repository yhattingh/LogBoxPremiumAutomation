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
import pageObjectsLogBox.PageObjectsActivityPage;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsHomePage;
import pageObjectsLogBox.PageObjectsPreAdmissionPage;

public class PreAdmissionTests extends BasePageFrameWork {
	// Instantiate Page Object Classes
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsPreAdmissionPage pageObjectsPreAdmissionPage = new PageObjectsPreAdmissionPage();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsActivityPage pageObjectsActivityPage = new PageObjectsActivityPage();

//	@AfterTest
//	public void cleanUpAfterTest() throws InterruptedException {
//		cleanUp();
//	}

	// User Story One
	@Test
	public void shouldOpenPreAdmissionPageAfterPatientSearch() throws IOException, InterruptedException {
		String patientName = "One";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch(patientName);
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		// Assert
		String patientNameHeader = pageObjectsPreAdmissionPage.getTextFromPreAdmissionPatientHeader();
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("preAdmission"));
		Assert.assertTrue(patientNameHeader.contains("One"));
		Reporter.log("URL contains \"preAdmission\"");
		Reporter.log("A new PreAdmission page was opened for patient: PreAdmission One");
	}

	// User Story Two
	@Test
	public void shouldNotAllowPreadmissionForDraftPatient() throws IOException, InterruptedException {

		String patientName = "Draft";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch(patientName);
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();

		// Assert: Validation displays
		String validationError = pageObjectsPreAdmissionPage.getTextfromDraftPatientErrorMessageOnPreadmission();
		Assert.assertTrue(validationError.contains("Cannot create pre-admission for a draft patient"));
		System.out.println(validationError);
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("premium/#/pre-admission/?context=pre-admissions"));
		Reporter.log("The pre-admission is not allowed for draft patients");

	}

	// User Story Three
	@Test
	public void shouldValidateRequiredFieldsWhenCreatingPreAdmission() throws IOException, InterruptedException {
		String patientName = "One";
		String instructions = "Test";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch(patientName);
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		pageObjectsPreAdmissionPage.enterSpecialInstructionsToPatient(instructions);
		pageObjectsPreAdmissionPage.clickSaveButton();

		// Assert: Validation displays
		String validationError = pageObjectsPreAdmissionPage
				.getTextFromPreAdmissionRequiredFieldsValidationPageMessage();
		Assert.assertTrue(validationError.contains("fill in the form"));
		String pageValidation = pageObjectsPreAdmissionPage.getTextFromPreAdmissionRequiredFieldsValidationErrorText();
		Assert.assertEquals(pageValidation, "The Expected Date of Admission is required.");
		Reporter.log("The Preadmission is validated on required fields");

	}

	// User Story Four
	@Test
	public void shouldCreatePreAdmissionFromEllipseOptionsWithRequiredFields()
			throws IOException, InterruptedException {

		String partialicd10code = "malignant";
		String icd10code1 = "Follow-up examination after chemotherapy for malignant neoplasm";
		String hospitalName = "Wits Donald Gordon Medical Centre";
		By pLocator = By.cssSelector("table");
		By pLocatorRow = By.cssSelector("table > tbody");
		String localDate;
		String localTimePlusTwoMinutes;
		String patientName = "PreAdmission One";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientName);
		pageObjectsHomePage.clickOnEllipseNextToPatientName();
		pageObjectsActivityPage.selectOptionFromMoreButtonList("Create Pre-Admission");
		System.out.println("Clicked on Create Pre-Admission");
		localDate = getLocalDate();
		localTimePlusTwoMinutes = getTimeWithAddSubstractMinutesUsedByStartTime(2);
		basePageLogBox.enterDate(localDate);
		basePageLogBox.enterTime(localTimePlusTwoMinutes);
		basePageLogBox.enterHospitalName(hospitalName);
		basePageLogBox.selectHospitalName(hospitalName);
		pageObjectsPreAdmissionPage.clickOnAddICD10CodesButton();
		basePageLogBox.searchForICD10Code(partialicd10code);
		basePageLogBox.selectICD10Code(icd10code1);
		basePageLogBox.clickAddButtonOnICD10CodeDialog();
		pageObjectsPreAdmissionPage.clickSaveButton();
		pageObjectsPreAdmissionPage.clickCloseButton();
		String preAdmissionTitle = pageObjectsPreAdmissionPage.getPreAdmissionTitle();
		Assert.assertEquals(preAdmissionTitle, "Pre-Admission for " + patientName);
		String admissionDetailsHeader = pageObjectsPreAdmissionPage.getAdmissionDetailsHeader();
		Assert.assertEquals(admissionDetailsHeader,"Admission Details");
		Reporter.log("The PreAdmission was created successfully");
	}

	// User Story Five
	@Test
	public void shouldDeletePreAdmissionFromPreAdmissionListOnSearchedPatientName() throws IOException, InterruptedException {
		String partialicd10code = "malignant";
		String icd10code1 = "Follow-up examination after chemotherapy for malignant neoplasm";
		String hospitalName = "Wits Donald Gordon Medical Centre";
		By pLocator = By.cssSelector("table");
		By pLocatorRow = By.cssSelector("table > tbody");
		String localDate;
		String localTimePlusTwoMinutes;
		String patientname = "PreAdmission Two";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientname);
		pageObjectsHomePage.clickOnEllipseNextToPatientName();
		pageObjectsActivityPage.selectOptionFromMoreButtonList("Create Pre-Admission");
		localDate = getLocalDate();
		localTimePlusTwoMinutes = getTimeWithAddSubstractMinutesUsedByStartTime(2);
		basePageLogBox.enterDate(localDate);
		basePageLogBox.enterTime(localTimePlusTwoMinutes);
		basePageLogBox.enterHospitalName(hospitalName);
		basePageLogBox.selectHospitalName(hospitalName);
		pageObjectsPreAdmissionPage.clickOnAddICD10CodesButton();
		basePageLogBox.searchForICD10Code(partialicd10code);
		basePageLogBox.selectICD10Code(icd10code1);
		basePageLogBox.clickAddButtonOnICD10CodeDialog();
		pageObjectsPreAdmissionPage.clickSaveButton();
		pageObjectsPreAdmissionPage.clickCloseButton();
		System.out.println("Pre-Admission created");
		basePageLogBox.clickHomeMainMenuItem();
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.filterOnPatientInPreadmissionList(patientname);
		//int numberOfPreAdmissionRowsBefore = basePageLogBox.getNumberOfTableRows(pLocator, pLocatorRow);
		pageObjectsPreAdmissionPage.deletePreAdmission();
		int numberOfPreAdmissionRowsAfter = basePageLogBox.getNumberOfTableRows(pLocator, pLocatorRow);
		//Assert.assertTrue(numberOfPreAdmissionRowsAfter < numberOfPreAdmissionRowsBefore);
		Assert.assertEquals(numberOfPreAdmissionRowsAfter, 0);
		Reporter.log("The PreAdmission was deleted successfully");
	}

	@Test
	public void shouldSendPreAdmissionToPatientEmailToComplete(String patientName) throws IOException {

	}

	@Test
	public void shouldViewPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {

	}

	@Test
	public void shouldEditPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {

	}

	@Test
	public void shouldPrintPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {

	}

	@Test
	public void shouldCompletePreAdmissionViaKiosk(String patientName) throws IOException {

	}

	@Test
	public void shouldDisplayNotificationInPracticeAfterCompletionOfPreAdmissionByPatient(String patientName)
			throws IOException {

	}

}
