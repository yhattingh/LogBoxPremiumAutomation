package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class BasePageGeoClock extends BasePageFrameWork {

	// Declare Homepage URL
	String homePageURL = "https://qa.logbox.co.za/premium/#/";

	// Method: Navigate Home
	public void navigateToHomePage() {
		driver.get(homePageURL);
		waitForUrl(30, "premium");
	}

	// Method: Click Activity Feed Main Menu Item
	public void clickActivityMainMenuItem() {
		clickElement(By.cssSelector("a:nth-of-type(2) > .v-btn__content"));
	}

	// Method: Click Activity Feed Main Menu Item
	public void clickHomeMainMenuItem() {
		clickElement(By.cssSelector("[data-cy='lb-toolbar-item-home'] .v-btn__content"));
	}

	// Method:Enter Current Date in Datepicker Fields
	public void enterDate(String desiredDate) throws InterruptedException {
		String dateSelector = "[data-cy='date-picker']";
		waitForElement(50, (By.cssSelector(dateSelector)));
		clickElement(By.cssSelector(dateSelector));
		clickElement(By.cssSelector("div:nth-of-type(2) .v-toolbar__content"));
		enterText(By.cssSelector(dateSelector), desiredDate);
	}

	// Method: Enter Time in Timepicker Fields
	public void enterTime(String desiredTime) throws InterruptedException {
		String timeSelector = "[data-cy='time-picker']";
		waitForElement(50, (By.cssSelector(timeSelector)));
		deleteText(By.cssSelector(timeSelector));
		enterText(By.cssSelector(timeSelector), desiredTime);
	}

	// Method: Enter a Hospital Name
	public void enterHospitalName(String hospitalName) throws InterruptedException {
		clickElement(By.cssSelector("[data-cy='lb-hospital-search']"));
		enterText(By.cssSelector("[data-cy='lb-hospital-search']"), hospitalName);
	}

	// Method: Select a Hospital from List
	public void selectHospitalName(String hospitalName) throws InterruptedException {
		waitForElement(50, (By.cssSelector("div[role='option']  .v-list-item__title")));
		clickElement(By.cssSelector("div[role='option']  .v-list-item__title"));
	}

	// Method: Search for ICD10Code
	public void searchForICD10Code(String icd10Code) throws InterruptedException {
		waitForElement(100, (By.cssSelector("[data-cy] .v-toolbar__title")));
		clickElement(By.cssSelector(".v-input--hide-details [type]"));
		enterText(By.cssSelector(".v-input--hide-details [type]"), icd10Code);
	}

	// Method: Select an ICD10Code
	public void selectICD10Code(String selectICD10Code) {
		clickElement(By.xpath("//*[contains(text(),'" + selectICD10Code + "')]"));
	}

	// Method: Add an ICD10Code
	public void clickAddButtonOnICD10CodeDialog() {
		clickElement(By.cssSelector(".primary--text.theme--light.v-btn.v-btn--text.v-size--default > .v-btn__content"));
	}
	
	// Method:  To Logout of LogBox
	public void logOutOfLogBox() {
		clickElement(By.cssSelector(".v-icon--right"));
		clickElement(By.xpath("//*[contains(text(),'" + "Logout" + "')]"));
		
	}
}
