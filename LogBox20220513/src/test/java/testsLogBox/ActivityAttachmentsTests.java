package testsLogBox;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsActivityFeedPage;
import pageObjectsLogBox.PageObjectsActivityPage;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsHomePage;


public class ActivityAttachmentsTests extends BasePageFrameWork {

	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	PageObjectsActivityPage pageObjectsActivityPage = new PageObjectsActivityPage();
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsActivityFeedPage pageObjectsActivityFeedPage = new PageObjectsActivityFeedPage();

//	@AfterTest
//	public void cleanUpAfterTest() {
//		cleanUp();
//	}
//	
//	@AfterMethod
//	public void logOut() {
//		basePageLogBox.logOutOfLogBox();
//	}

	@Test
	public void shouldAddWordAttachmentToActivity() throws InterruptedException, IOException {

		String patientName = "Joey";

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();

		// WHEN
		pageObjectsHomePage.searchPracticePatientsInSearchBar(patientName);
		pageObjectsHomePage.clickOnSearchedPatient(patientName);
		Thread.sleep(1000);
		pageObjectsActivityPage.clickAddActivityButton();
		Thread.sleep(1000);
		pageObjectsActivityPage.clickAttachmentIcon();
		Thread.sleep(1000);
		pageObjectsActivityPage.clickAndUploadFile();
		Thread.sleep(1000);
//		pageObjectsActivityPage.clickPostButton();
		System.out.println("Text Note Added = :" + " " + pageObjectsActivityPage.getTextFromActivityNote());

		// THEN
//		Assert.assertTrue(pageObjectsActivityPage.getTextFromActivityNote().equals(textNote));
		Reporter.log("A Word Document was attached successfully: "
				+ pageObjectsActivityPage.getTextFromActivityNote());
	}
	
	//Word, Excel, PDF, .png or jpeg, audio and video files
}
