package pageObjectsLogBox;

import java.io.File;
import java.util.Calendar;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class BasePageLogBox extends BasePageFrameWork {

	// Declare Homepage Url
	String homePageURL = "https://qa.logbox.co.za/premium/#/";

	// Method: Navigate Home
	public void navigateToHomePage() {
		driver.get(homePageURL);
		waitForUrl(30, "premium");
	}

	// Method:Refresh page
	public void refreshPageOnPatientList() {
		clickElement(By.xpath("//div[@id='recently-added-patients']/div/div[1]/div//i[.='refresh']"));
		waitForElement(200, (By.cssSelector("tbody .pointer:nth-of-type(1) .text-start")));
	}

	// Method: Click Activity Feed main menu item

	// Method: Click Practice main menu item

	// Method:Enter current date in datepicker fields
	public void enterDate(String desiredDate) throws InterruptedException {
		String dateSelector = "[data-cy='date-picker']";
		waitForElement(50, (By.cssSelector(dateSelector)));
		clickElement(By.cssSelector(dateSelector));
		clickElement(By.cssSelector("div:nth-of-type(2) .v-toolbar__content"));
		enterText(By.cssSelector(dateSelector), desiredDate);

	}

	// Method:Enter time in timepicker fields
	public void enterTime(String desiredTime) throws InterruptedException {
		String timeSelector = "[data-cy='time-picker']";
		waitForElement(50, (By.cssSelector(timeSelector)));
		deleteText(By.cssSelector(timeSelector));
		enterText(By.cssSelector(timeSelector), desiredTime);
	}

}
