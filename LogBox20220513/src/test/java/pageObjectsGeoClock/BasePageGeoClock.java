package pageObjectsGeoClock;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;
import frameWork.BasePageFrameWorkGeoClock;

public class BasePageGeoClock extends BasePageFrameWorkGeoClock {

	// Declare Page URL's
	String loginPageURL = "https://labs-dev.geoclock.net/#/login";
	String employeeTimePageURL = "https://labs-dev.geoclock.net/#/approvals";
	String employeeSchedulePageURL = "https://labs-dev.geoclock.net/#/approver-work-schedules";
	String employeeClockingPageURL = "https://labs-dev.geoclock.net/#/approver-clocking";
	String myTimePageURL = "https://labs-dev.geoclock.net/#/time/time-pairs";
	String myClockingPageURL = "https://labs-dev.geoclock.net/#/time/clock";
	String mySchedulePageURL = "https://labs-dev.geoclock.net/#/employee-work-schedules";
	String facialRecognitionPageURL = "https://labs-dev.geoclock.net/#/facial-recognition";
	String settingsPageURL = "https://labs-dev.geoclock.net/#/settings";

	
	// Method: Navigate Employee Time Page
	public void navigateEmployeeTimePage() {
		driver.get(employeeTimePageURL);
		waitForUrl(30, "approvals");
	}

	// Method: Navigate Employee Schedule Page
	public void navigateEmployeeSchedulePage() {
		driver.get(employeeSchedulePageURL);
		waitForUrl(30, "approver-work-schedules");
	}

	// Method: Navigate Employee Clocking Page
	public void navigateEmployeeClockingPage() {
		driver.get(employeeClockingPageURL);
		waitForUrl(30, "approver-clocking");
	}

	// Method: Navigate My Time Page
	public void navigateMyTimePage() {
		driver.get(myTimePageURL);
		waitForUrl(30, "time/time-pairs");
	}

	// Method: Navigate My Clocking Page
	public void navigateMyClockingPage() {
		driver.get(myClockingPageURL);
		waitForUrl(30, "time/clock");
	}

	// Method: Navigate My schedule Page
	public void navigateMySchedulePage() {
		driver.get(mySchedulePageURL);
		waitForUrl(30, "employee-work-schedules");
	}

	// Method: Navigate My schedule Page
	public void navigateFacialRecognitionPage() {
		driver.get(facialRecognitionPageURL);
		waitForUrl(30, "facial-recognition");
	}

	// Method: Navigate settings Page
	public void navigateSettingsPage() {
		driver.get(settingsPageURL);
		waitForUrl(30, "settings");
	}

	// Method: To log out
	public void logOutGeoClock() {
	 clickElement(By.xpath("//div[contains(text(),'Log Out')]"));
	}

}
