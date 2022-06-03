package testsLogBox.ActivityFeedTests;

import java.io.IOException;

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
	
	@Test
	public void shouldCreateCaseFileForSelectedPatient() throws InterruptedException, IOException {
		
		String textFromFirstItemOnCaseFilesList;
		String textFromSecondItemOnCaseFileList;
		String localTime;
		
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertUsernameAndPasswordFromExcel(1,1,2);
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsOnHomePage("Jane");
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList("Create Case File");
		localTime = pageObjectsActivityPage.getLocalTime();
		System.out.println("CURRENT LOCAL TIME" + " " + localTime);
		pageObjectsActivityPage.inputinputCaseFileProblem("Case file added on" + " " + localTime);
		pageObjectsActivityPage.clickSaveButtonCaseFile();
		pageObjectsActivityPage.clickCaseFilesDropdown();
		textFromFirstItemOnCaseFilesList = pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList();
		System.out.println("The latest item on the list is: " + " " + textFromFirstItemOnCaseFilesList);
		textFromSecondItemOnCaseFileList = pageObjectsActivityPage.getTextFromSecondItemOnCaseFilesList();
		System.out.println("The second item on the list is: :" + " " + textFromSecondItemOnCaseFileList);
		String feedURL = driver.getCurrentUrl();
		Assert.assertTrue(feedURL.contains("feed"));
		Assert.assertTrue(pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList().equals("Case file added on" + " " + localTime));
		Reporter.log("URL contains \"feed\"");
		Reporter.log("The following file was created successfully: " + pageObjectsActivityPage.getTextFromFirstItemOnCaseFilesList());
	}

}
