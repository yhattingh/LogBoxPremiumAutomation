package testsLogBox;

import java.io.IOException;

import org.openqa.selenium.By;
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
import pageObjectsLogBox.PageObjectsPreAdmissionPage;

public class PreAdmissionTests extends BasePageFrameWork {
	// Instantiate Page Object Classes
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsPreAdmissionPage pageObjectsPreAdmissionPage = new PageObjectsPreAdmissionPage();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsActivityPage pageObjectsActivityPage = new PageObjectsActivityPage();

	@AfterTest
	public void cleanUpAfterTest() {
		cleanUp();
	}
	
	@AfterMethod
	public void logOut() {
		basePageLogBox.logOutOfLogBox();
	}

	// User Story One: CH
	@Test
	public void shouldOpenPreAdmissionPageAfterPatientSearch() throws IOException, InterruptedException {

		String patientName = "One";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch(patientName);
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		// THEN
		String patientNameHeader = pageObjectsPreAdmissionPage.getTextFromPreAdmissionPatientHeader();
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("preAdmission"));
		Assert.assertTrue(patientNameHeader.contains("One"));
		Reporter.log("URL contains \"preAdmission\"");
		Reporter.log("A new PreAdmission page was opened for patient: PreAdmission One");
	}

	// User Story Two: CH
	@Test
	public void shouldNotAllowPreadmissionForDraftPatient() throws IOException, InterruptedException {

		String patientName = "Draft";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch(patientName);
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		// THEN
		String validationError = pageObjectsPreAdmissionPage.getTextfromDraftPatientErrorMessageOnPreadmission();
		Assert.assertTrue(validationError.contains("Cannot create pre-admission for a draft patient"));
		System.out.println(validationError);
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("premium/#/pre-admission/?context=pre-admissions"));
		Reporter.log("The pre-admission is not allowed for draft patients");

	}

	// User Story Three: CH
	@Test(dataProvider = "PreAdmissionPatients", dataProviderClass = ReadDataFromExcel.class)
	public void shouldValidateRequiredFieldsWhenCreatingPreAdmission(String patientName)
			throws IOException, InterruptedException {

		String patientNameInput = patientName;
		String instructions = "Test";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.clickCreatePreAdmissionButton();
		pageObjectsPreAdmissionPage.clickPreAdmissionPatientSearchField();
		pageObjectsPreAdmissionPage.enterPreAdmissionPatientNameToSearch(patientNameInput);
		pageObjectsPreAdmissionPage.selectSearchedPatientOnPreAdmission();
		pageObjectsPreAdmissionPage.clickSelectAfterPreAdmissionPatientSearch();
		pageObjectsPreAdmissionPage.enterSpecialInstructionsToPatient(instructions);
		pageObjectsPreAdmissionPage.clickSaveButton();
		// THEN
		String validationError = pageObjectsPreAdmissionPage
				.getTextFromPreAdmissionRequiredFieldsValidationPageMessage();
		Assert.assertTrue(validationError.contains("fill in the form"));
		String pageValidation = pageObjectsPreAdmissionPage.getTextFromPreAdmissionRequiredFieldsValidationErrorText();
		Assert.assertEquals(pageValidation, "The Expected Date of Admission is required.");
		Reporter.log("The Preadmission is validated on required fields");
	}

	// User Story Four: CH
	@Test
	public void shouldCreatePreAdmissionFromEllipseOptionsWithRequiredFields()
			throws IOException, InterruptedException {

		String partialicd10code = "malignant";
		String icd10code1 = "Follow-up examination after chemotherapy for malignant neoplasm";
		String hospitalName = "Wits Donald Gordon Medical Centre";
		String localDate;
		String localTimePlusTwoMinutes;
		String patientName = "PreAdmission One";
		By pLocatorTable = By.cssSelector("table");
		By pLocatorRow = By.xpath("//tbody/tr");

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		int numberOfPreAdmissionRowsBefore = basePageLogBox.getNumberOfTableRows(pLocatorTable, pLocatorRow);
		basePageLogBox.navigateToHomePage();
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientName);
		//Thread.sleep(1000);
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
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		//Thread.sleep(1000);
		pageObjectsHomePage.clickRefreshButton();
		int numberOfPreAdmissionRowsAfter = basePageLogBox.getNumberOfTableRows(pLocatorTable, pLocatorRow);
		boolean checkRowsAfterGreaterThanBefore = pageObjectsPreAdmissionPage.preAdmissionsAfterIsGreaterThanBefore(numberOfPreAdmissionRowsBefore, numberOfPreAdmissionRowsAfter);
		// THEN
		Assert.assertEquals(checkRowsAfterGreaterThanBefore, true);
		Reporter.log("The PreAdmission was created successfully");
	}

	// User Story Five: CH
	@Test
	public void shouldDeletePreAdmissionFromPreAdmissionListOnSearchedPatientName()
			throws IOException, InterruptedException {

		String partialicd10code = "bladder";
		String icd10code1 = "Malignant neoplasm, trigone of bladder";
		String hospitalName = "Wits Donald Gordon Medical Centre";
		String localDate;
		String localTimePlusTwoMinutes;
		String patientname = "Two";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
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
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		pageObjectsPreAdmissionPage.filterOnPatientInPreadmissionList(patientname);
		pageObjectsPreAdmissionPage.deletePreAdmission();
		pageObjectsPreAdmissionPage.confirmPreAdmissionDelete();
		// THEN
		String rowText = pageObjectsPreAdmissionPage.getTextFromTableFirstRow();
		Assert.assertEquals(rowText, "No data available");
		Reporter.log("The PreAdmission was deleted successfully");
	}
	
	
	@Test(enabled=false)
	public void shouldDisplaySuccessMessageAfterPreAdmissionIsCreated()
			throws IOException, InterruptedException {

		String partialicd10code = "malignant";
		String icd10code1 = "Follow-up examination after chemotherapy for malignant neoplasm";
		String hospitalName = "Wits Donald Gordon Medical Centre";
		String localDate;
		String localTimePlusTwoMinutes;
		String patientName = "PreAdmission One";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertPreAdmissionUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		// WHEN
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientName);
		//Thread.sleep(1000);
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
		pageObjectsHomePage.clickOnPreAdmissionButtonInLeftMenu();
		//Thread.sleep(1000);
		pageObjectsHomePage.clickRefreshButton();
		// THEN
		String successToastText = pageObjectsPreAdmissionPage.getTextFromSuccessMessage();
		Assert.assertTrue(successToastText.contains("Pre-Admission has been created"));
		Reporter.log("The PreAdmission was created successfully");
	}

	@Test(enabled = false)
	public void shouldSendPreAdmissionToPatientEmailToComplete(String patientName) throws IOException {

	}

	@Test(enabled = false)
	public void shouldViewPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {

	}

	@Test(enabled = false)
	public void shouldEditPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {

	}

	@Test(enabled = false)
	public void shouldPrintPreAdmissionFromPreAdmissionScreen(String patientName) throws IOException {

	}

	@Test(enabled = false)
	public void shouldCompletePreAdmissionViaKiosk(String patientName) throws IOException {

	}

	@Test(enabled = false)
	public void shouldDisplayNotificationInPracticeAfterCompletionOfPreAdmissionByPatient(String patientName)
			throws IOException {

	}
}
