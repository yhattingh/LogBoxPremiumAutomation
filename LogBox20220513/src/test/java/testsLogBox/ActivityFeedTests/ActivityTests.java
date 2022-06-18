package testsLogBox.ActivityFeedTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
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
	BasePageFrameWork basePageFrameWork = new BasePageFrameWork();
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsActivityFeedPage pageObjectsActivityFeedPage = new PageObjectsActivityFeedPage();

	// User Story One
	@Test
	public void shouldCreateCaseFileForSelectedPatient() throws InterruptedException, IOException {

		String textFromFirstItemOnCaseFilesList;
		String localDateTime;
		String patientName = "John";
		String selectOptionFromButtonList = "Create Case File";
		String inputOnCaseFile = "Case file added on";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsOnHomePage(patientName);
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList(selectOptionFromButtonList);
		localDateTime = basePageFrameWork.getLocalDateTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localDateTime);
		pageObjectsActivityPage.inputCaseFilePresentingProblem(inputOnCaseFile + " " + localDateTime);
		pageObjectsActivityPage.clickCaseFileDialogSaveButton();
		pageObjectsActivityPage.waitForCaseFileCreatedSnackContentToNoLongerDisplay();
		textFromFirstItemOnCaseFilesList = pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		System.out.println("The latest item on the list is: " + " " + textFromFirstItemOnCaseFilesList);
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("feed"));
		Assert.assertTrue(pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList()
				.equals("Case file added on" + " " + localDateTime));
		Reporter.log("URL contains \"feed\"");
		Reporter.log("The following file was created successfully: "
				+ pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList());
	}

	// User Story Two
	@Test
	public void shouldCreateActivityForSelectedPatient() throws InterruptedException, IOException {

		String localDateTime;
		String selectedCaseFile = "Case file added on 13-06-2022 22:01:43";
		String textNote = "This is a new activity note created on ";
		String patientName = "John";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsOnHomePage(patientName);
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickCaseFilesDropdown();
		pageObjectsActivityPage.selectItemFromDropdownList(selectedCaseFile);
		System.out.println("Case File number entered: " + " " + selectedCaseFile);
		pageObjectsActivityPage.clickAddActivityButton();
		localDateTime = basePageFrameWork.getLocalDateTime();
		pageObjectsActivityPage.addTextNote(textNote + localDateTime);
		pageObjectsActivityPage.clickPostButton();
		System.out.println("Text Note Added = :" + " " + pageObjectsActivityPage.getTextFromActivityNote());
		Assert.assertTrue(pageObjectsActivityPage.getTextFromActivityNote().equals(textNote + localDateTime));
		Reporter.log("The following activity was created successfully: "
				+ pageObjectsActivityPage.getTextFromActivityNote());
	}

	// User Story Three
	@Test
	public void shouldRemoveDiagnosticCodeForAnActivity() throws InterruptedException, IOException {
		String localDateTime;
		String patientName = "John";
		String selectOptionFromButtonList = "Create Case File";
		String inputOnCaseFile = "Case file added on";
		String textNote = "Add diagnostic codes for theater @";
		String categorytext = "Theatre";
		String icd10Code = "hip";
		String selectICD10Code1 = "Clicking hip";
		String selectICD10Code2 = "Congenital subluxation of hip, bilateral";

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsOnHomePage(patientName);
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList(selectOptionFromButtonList);
		localDateTime = basePageFrameWork.getLocalDateTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localDateTime);
		pageObjectsActivityPage.inputCaseFilePresentingProblem(inputOnCaseFile + " " + localDateTime);
		pageObjectsActivityPage.clickCaseFileDialogSaveButton();
		pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		pageObjectsActivityPage.clickAddActivityButton();
		pageObjectsActivityPage.selectCategory(categorytext);
		localDateTime = basePageFrameWork.getLocalDateTime();
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
		Assert.assertTrue(pageObjectsActivityPage.getTextForSelectedDiagnosisCodes().contains(selectICD10Code2));
		Reporter.log("One diagnosis code removed successfully.  Diagnosis code remaining:" + " "
				+ pageObjectsActivityPage.getTextForSelectedDiagnosisCodes());
	}

	// User story four
	@Test
	public void shouldSelectInpatientLocation() throws InterruptedException, IOException {

		String searchedActivity = "Case file added on 14-06-2022 08:28:34";
		
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		basePageLogBox.clickActivityMainMenuItem();
		pageObjectsActivityFeedPage.searchForActivity(searchedActivity);
		pageObjectsActivityFeedPage.clickActivity();
		pageObjectsActivityFeedPage.clickToEditActivity();
		pageObjectsActivityFeedPage.clickInpatientOutpatientButton();
//		pageObjectsActivityFeedPage.selectInpatientRadioButton();
		pageObjectsActivityFeedPage.clickInpatientRadioButton();
		pageObjectsActivityFeedPage.clickSetButtonOnLocationDialogBox();

	}
}
