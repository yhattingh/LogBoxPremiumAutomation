package pageObjectsLogBox;

import java.io.File;
import java.time.LocalDate;
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

	// Method: Click Activity Feed Main Menu Item
	public void clickActivityMainMenuItem() {
		clickElement(By.cssSelector("a:nth-of-type(2) > .v-btn__content"));
	}

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
