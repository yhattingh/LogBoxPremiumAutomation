package testsLogBox.ActivityFeedTests;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hamcrest.core.StringContains;
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
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertUsernameAndPasswordFromExcel(1,1,2);
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsOnHomePage("Jane");
		pageObjectsHomePage.clickOnSearchedPatient();
		pageObjectsActivityPage.clickMoreButton();
		pageObjectsActivityPage.selectOptionFromMoreButtonList("Create Case File");
		
		//add a timestamp variable
		LocalDateTime localDateTime = LocalDateTime.now();
		String localTime = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

		System.out.println("CURRENT LOCAL TIME" + " " + localTime);
		pageObjectsActivityPage.inputinputCaseFileProblem("Case file added on" + " " + localTime);
		pageObjectsActivityPage.clickSaveButtonCaseFile();
		//String feedURL = driver.getCurrentUrl();
		//Assert.assertTrue(feedURL.contains("feed"));
		//Reporter.log("User ");
	}

}
