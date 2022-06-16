package testsLogBox.ActivityFeedTests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import pageObjectsLogBox.PageObjectsActivityPage;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsHomePage;

public class ActivityTests extends BasePageFrameWork{
	
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsActivityPage pageObjectsActivityPage = new PageObjectsActivityPage();
	BasePageFrameWork basePageFrameWork = new BasePageFrameWork();
	
	
	
	//User Story One
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
		pageObjectsActivityPage.searchPracticePatientsOnHomePage(patientName);
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList(selectOptionFromButtonList);
		localDateTime = basePageFrameWork.getLocalDateTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localDateTime);
		pageObjectsActivityPage.inputCaseFileProblem(inputOnCaseFile + " " + localDateTime);
		pageObjectsActivityPage.clickCaseFileDialogSaveButton();
		pageObjectsActivityPage.waitForCaseFileCreatedSnackContentToNoLongerDisplay();
		textFromFirstItemOnCaseFilesList = pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		System.out.println("The latest item on the list is: " + " " + textFromFirstItemOnCaseFilesList);
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("feed"));
		Assert.assertTrue(pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList().equals("Case file added on" + " " + localDateTime));
		Reporter.log("URL contains \"feed\"");
		Reporter.log("The following file was created successfully: " + pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList());
	}
	
	//User Story Two
	@Test
	public void shouldCreateActivityForSelectedPatient() throws InterruptedException, IOException {
		
		String localDateTime;
//		String selectedCaseFile = "Initial case file";
		String selectedCaseFile = "Case file added on 13-06-2022 22:01:43";
		String textNote = "This is a new activity note created on ";
		String patientName = "John";
	
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsActivityPage.searchPracticePatientsOnHomePage(patientName);
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickCaseFilesDropdown();
//		pageObjectsActivityPage.selectCaseFileFromDropdownList(selectedCaseFile);
		pageObjectsActivityPage.selectItemFromDropdownList(selectedCaseFile);
		System.out.println("Case File number entered: " + " " + selectedCaseFile);
		pageObjectsActivityPage.clickAddActivityButton();
		localDateTime = basePageFrameWork.getLocalDateTime();
		pageObjectsActivityPage.addTextNote(textNote + localDateTime);
		pageObjectsActivityPage.clickPostButton();
		System.out.println("Text Note Added = :" + " " + pageObjectsActivityPage.getTextFromActivityNote());
		Assert.assertTrue(pageObjectsActivityPage.getTextFromActivityNote().equals(textNote + localDateTime));
		Reporter.log("The following activity was created successfully: " +  pageObjectsActivityPage.getTextFromActivityNote());
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
		pageObjectsActivityPage.searchPracticePatientsOnHomePage(patientName);
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList(selectOptionFromButtonList);
		localDateTime = basePageFrameWork.getLocalDateTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localDateTime);
		pageObjectsActivityPage.inputCaseFileProblem(inputOnCaseFile + " " + localDateTime);
		pageObjectsActivityPage.clickCaseFileDialogSaveButton();
		pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		pageObjectsActivityPage.clickAddActivityButton();
		pageObjectsActivityPage.selectCategory(categorytext);
		localDateTime = basePageFrameWork.getLocalDateTime();
		pageObjectsActivityPage.addTextNote(textNote + localDateTime);
		pageObjectsActivityPage.clickDiagnosisActionIcon();
		pageObjectsActivityPage.searchForICD10Code(icd10Code);
		pageObjectsActivityPage.selectICD10Code(selectICD10Code1);
		pageObjectsActivityPage.selectICD10Code(selectICD10Code2);
		pageObjectsActivityPage.clickAddButtonOnICD10CodeDialog();
		pageObjectsActivityPage.clickActivityPostButton();
		System.out.println("ICD10 Codes selected :" + " " + selectICD10Code1 + " " +  ";" + " " + selectICD10Code2);
		pageObjectsActivityPage.clickActivityFeedToUpdateActivity();
		pageObjectsActivityPage.removeICD10Code(selectICD10Code1);
		pageObjectsActivityPage.clickActivityPostButton();
		//pageObjectsActivityPage.clickRemoveButton(selectICD10Code2);
		System.out.println("Codes after remove action: " + " " + pageObjectsActivityPage.getTextForSelectedDiagnosisCodes());
		Assert.assertTrue(pageObjectsActivityPage.getTextForSelectedDiagnosisCodes().contains(selectICD10Code2));
		Reporter.log("One diagnosis code removed successfully.  Diagnosis code remaining:" + " " + pageObjectsActivityPage.getTextForSelectedDiagnosisCodes());

	}

}
