package testsLogBox;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsHomePage;
import pageObjectsLogBox.PageObjectsMessagePatientPage;

public class HomePageTests extends BasePageFrameWork{
	
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsMessagePatientPage pageObjectsMessagePatientPage = new PageObjectsMessagePatientPage();
	
	@Test
	public void shouldSearchForPatient() throws InterruptedException, IOException {
	
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertUsernameAndPasswordFromExcel(1,1,2);
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsInSearchBar("John");
	}
	
	@Test
	public void shouldOpenMessagePatientPage() {
		String pageTitle = pageObjectsMessagePatientPage.getMessagePatientPageTitle();
		Assert.assertEquals("Message Patient", pageTitle);
		Reporter.log("The Message Patient page opened successfully");
	}
}
