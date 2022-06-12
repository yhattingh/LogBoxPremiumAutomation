package testsLogBox.ActivityFeedTests;

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
		
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();;
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsOnHomePage("Jane");
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList("Create Case File");
		localDateTime = basePageFrameWork.getLocalDateTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localDateTime);
		pageObjectsActivityPage.inputCaseFileProblem("Case file added on" + " " + localDateTime);
		pageObjectsActivityPage.clickSaveButtonCaseFile();
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
		String selectedCaseFile = "Case file added on 11-06-2022 13:07:29";
//		String selectedCaseFile = "test";
		String textNote = "This is a new activity note";
	

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();;
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
    	pageObjectsHomePage.clickInSearchPracticePatient();
		pageObjectsHomePage.searchPracticePatientsOnHomePage("Jane");
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickCaseFilesDropdown();
		pageObjectsActivityPage.selectAnItemInDropdownList(selectedCaseFile);
		//pageObjectsActivityPage.selectItemFromDropdownList(selectedCaseFile);
		System.out.println("Case File number entered: " + " " + selectedCaseFile);
		pageObjectsActivityPage.clickAddActivityButton();
		localDateTime = basePageFrameWork.getLocalDateTime();
		pageObjectsActivityPage.addTextNote(textNote + localDateTime);
		pageObjectsActivityPage.clickPostButton();
		System.out.println("Text Note Added= :" + " " + pageObjectsActivityPage.getTextFromActivityNote());
		Assert.assertTrue(pageObjectsActivityPage.getTextFromActivityNote().equals(textNote + localDateTime));
		Reporter.log("The following activity was created successfully: " +  pageObjectsActivityPage.getTextFromActivityNote());
		
		
		
//		String feedURL = driver.getCurrentUrl();
//		Assert.assertTrue(feedURL.contains("feed"));
//		Assert.assertTrue(pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList().equals("Case file added on" + " " + localDateTime));
//		Reporter.log("URL contains \"feed\"");
//		Reporter.log("The following file was created successfully: " + pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList());
	}
	
	// User Story Three
	
	@Test
	public void shouldDoSomething() {

	}

}
