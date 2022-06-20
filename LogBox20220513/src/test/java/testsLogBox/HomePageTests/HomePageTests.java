package testsLogBox.HomePageTests;

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

public class HomePageTests extends BasePageFrameWork{
	
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	
	@Test
	public void shouldSearchForPatient() throws InterruptedException, IOException {
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertUsernameAndPasswordFromExcel(1,1,2);
    	pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		pageObjectsHomePage.searchPracticePatientsInSearchBar("John");
		WebElement searchedPatient = driver.findElement(By.cssSelector("[data-cy] tr td:nth-of-type(2)"));
		//searchedPatient.toString();
		//Assert.assertTrue(searchedPatient.contains("John"));
		
	}
	
}
