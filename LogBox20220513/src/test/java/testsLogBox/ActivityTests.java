package testsLogBox;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsActivityFeedPage;
import pageObjectsLogBox.PageObjectsActivityPage;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsHomePage;

public class ActivityTests extends BasePageFrameWork {

	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsActivityPage pageObjectsActivityPage = new PageObjectsActivityPage();
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsActivityFeedPage pageObjectsActivityFeedPage = new PageObjectsActivityFeedPage();

	@AfterTest
	public void cleanUpAfterTest() {
		cleanUp();
	}

	// User Story One:  YH
	@Test
	public void shouldCreateCaseFileForSelectedPatient() throws InterruptedException, IOException {

		String textFromFirstItemOnCaseFilesList;
		String localDateTime;
		String patientName = "Joshua";
		String selectOptionFromButtonList = "Create Case File";
		String inputOnCaseFile = "Case file added on";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();

		// WHEN
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientName);
		pageObjectsHomePage.clickOnSearchedPatient(patientName);
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList(selectOptionFromButtonList);
		localDateTime = getLocalDateTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localDateTime);
		pageObjectsActivityPage.inputCaseFilePresentingProblem(inputOnCaseFile + " " + localDateTime);
		pageObjectsActivityPage.clickCaseFileDialogSaveButton();
		pageObjectsActivityPage.waitForCaseFileCreatedSnackContentToNoLongerDisplay();
		textFromFirstItemOnCaseFilesList = pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		System.out.println("The latest item on the list is: " + " " + textFromFirstItemOnCaseFilesList);
		String feedURL = driver.getCurrentUrl();

		// THEN
		Assert.assertTrue(feedURL.contains("feed"));
		Assert.assertTrue(pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList()
				.equals("Case file added on" + " " + localDateTime));
		Reporter.log("URL contains \"feed\"");
		Reporter.log("The following file was created successfully: "
				+ pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList());
	}

	// User Story Two:  YH
	@Test
	public void shouldCreateActivityForSelectedPatient() throws InterruptedException, IOException {

		String localDateTime;
		String selectedCaseFile = "Initial case file";
		String textNote = "This is a new activity note created on ";
		String patientName = "Joshua";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();

		// WHEN
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientName);
		pageObjectsHomePage.clickOnSearchedPatient(patientName);
		pageObjectsActivityPage.clickCaseFilesDropdown();
		pageObjectsActivityPage.selectItemFromDropdownList(selectedCaseFile);
		System.out.println("Case File number entered: " + " " + selectedCaseFile);
		pageObjectsActivityPage.clickAddActivityButton();
		localDateTime = getLocalDateTime();
		pageObjectsActivityPage.addTextNote(textNote + localDateTime);
		pageObjectsActivityPage.clickPostButton();
		System.out.println("Text Note Added = :" + " " + pageObjectsActivityPage.getTextFromActivityNote());

		// THEN
		Assert.assertTrue(pageObjectsActivityPage.getTextFromActivityNote().equals(textNote + localDateTime));
		Reporter.log("The following activity was created successfully: "
				+ pageObjectsActivityPage.getTextFromActivityNote());
	}

	// User Story Three:  YH
	@Test
	public void shouldRemoveDiagnosticCodeForAnActivity() throws InterruptedException, IOException {
		String localDateTime;
		String patientName = "Joshua";
		String selectOptionFromButtonList = "Create Case File";
		String inputOnCaseFile = "Case file added on";
		String textNote = "Add diagnostic codes for theater @";
		String categorytext = "Theatre";
		String icd10Code = "hip";
		String selectICD10Code1 = "Clicking hip";
		String selectICD10Code2 = "Congenital subluxation of hip, bilateral";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();

		// WHEN
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientName);
		pageObjectsHomePage.clickOnSearchedPatient(patientName);
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList(selectOptionFromButtonList);
		localDateTime = getLocalDateTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localDateTime);
		pageObjectsActivityPage.inputCaseFilePresentingProblem(inputOnCaseFile + " " + localDateTime);
		pageObjectsActivityPage.clickCaseFileDialogSaveButton();
		pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		pageObjectsActivityPage.clickAddActivityButton();
		pageObjectsActivityPage.selectCategory(categorytext);
		localDateTime = getLocalDateTime();
		pageObjectsActivityPage.addTextNote(textNote + localDateTime);
		pageObjectsActivityPage.clickDiagnosisActionIcon();
		basePageLogBox.searchForICD10Code(icd10Code);
		basePageLogBox.selectICD10Code(selectICD10Code1);
		basePageLogBox.selectICD10Code(selectICD10Code2);
		basePageLogBox.clickAddButtonOnICD10CodeDialog();
		pageObjectsActivityPage.clickActivityPostButton();
		System.out.println("ICD10 Codes selected :" + " " + selectICD10Code1 + " " + ";" + " " + selectICD10Code2);
		pageObjectsActivityPage.clickOnActivityFeedToUpdateActivity();
		pageObjectsActivityPage.clickRemoveButton(selectICD10Code1);
		pageObjectsActivityPage.clickActivityPostButton();
		System.out.println(
				"Codes after remove action: " + " " + pageObjectsActivityPage.getTextForSelectedDiagnosisCodes());

		// THEN
		Assert.assertTrue(pageObjectsActivityPage.getTextForSelectedDiagnosisCodes().contains(selectICD10Code2));
		Reporter.log("One diagnosis code removed successfully.  Diagnosis code remaining:" + " "
				+ pageObjectsActivityPage.getTextForSelectedDiagnosisCodes());
	}

	// User story four:  YH
	@Test(enabled = true)
	public void shouldSelectInpatientLocation() throws InterruptedException, IOException {

		String searchedActivity = "Case file added on 14-06-2022 08:28:34";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();

		// WHEN
		basePageLogBox.clickActivityMainMenuItem();
		pageObjectsActivityFeedPage.searchForActivity(searchedActivity);
		pageObjectsActivityFeedPage.clickSearchedActivity(searchedActivity);
		pageObjectsActivityFeedPage.clickToEditActivity();
		pageObjectsActivityFeedPage.clickInpatientOutpatientButton();
		pageObjectsActivityFeedPage.clickSetButtonOnLocationDialogBox();
		System.out.println("Option selected: " + " " + pageObjectsActivityFeedPage.getTextForInOutPatient());

		// THEN
		Assert.assertTrue(pageObjectsActivityFeedPage.getTextForInOutPatient().equals("Outpatient"));
		Reporter.log("Patient Location successfully selected as:" + " "
				+ pageObjectsActivityFeedPage.getTextForInOutPatient());
	}
}
