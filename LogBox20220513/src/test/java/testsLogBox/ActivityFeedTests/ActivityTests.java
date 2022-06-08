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
		pageObjectsActivityPage.clickCaseFilesDropdown();
		textFromFirstItemOnCaseFilesList = pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		System.out.println("The latest item on the list is: " + " " + textFromFirstItemOnCaseFilesList);
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("feed"));
		Assert.assertTrue(pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList().equals("Case file added on" + " " + localDateTime));
		Reporter.log("URL contains \"feed\"");
		Reporter.log("The following file was created successfully: " + pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList());
	}
	
	@Test
	public void shouldCreateActivityForSelectedPatient() throws InterruptedException, IOException {
		
		String localDateTime;

		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();;
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
    	pageObjectsHomePage.clickInSearchPracticePatient();
		pageObjectsHomePage.searchPracticePatientsOnHomePage("John");
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickCaseFilesDropdown();
		pageObjectsActivityPage.main1(null);
		
//		String feedURL = driver.getCurrentUrl();
//		Assert.assertTrue(feedURL.contains("feed"));
//		Assert.assertTrue(pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList().equals("Case file added on" + " " + localDateTime));
//		Reporter.log("URL contains \"feed\"");
//		Reporter.log("The following file was created successfully: " + pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList());
	}
	
	@Test
	public void yhtestinglists() throws InterruptedException, IOException {
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();;
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsOnHomePage("Jane");
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickCaseFilesDropdown();
		pageObjectsActivityPage.anotherList(null);
		//pageObjectsActivityPage.getList();
		//pageObjectsActivityPage.yhList(null);

	}

}
