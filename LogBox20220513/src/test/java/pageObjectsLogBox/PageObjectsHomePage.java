package pageObjectsLogBox;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.record.RefreshAllRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;

public class PageObjectsHomePage extends BasePageFrameWork {

	public void searchPracticePatientsInSearchBar(String patientname) throws InterruptedException {
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);
		// waitUntilElementNoLongerDisplays(5,By.cssSelector("tbody.pointer:nth-of-type(2)
		// .text-left:nth-of-type(4)"));
	}

	public void clickOnSearchedPatient(String patientFirstName) {
		String returnedPatientName = getElementText(
				By.cssSelector("tbody .pointer:nth-of-type(1) .text-left:nth-of-type(2)"));
		if (returnedPatientName.equals(patientFirstName)) {
			clickElement(By.cssSelector("tbody .pointer:nth-of-type(1) .text-start"));
		} else {
			waitUntilElementNoLongerDisplays(5, By.className(".pageLoadingBar"));
			clickElement(By.cssSelector("tbody .pointer:nth-of-type(1) .text-start"));
		}
	}

	public void clickOnEllipseNextToPatientName() {
		clickElement(By.cssSelector("[class='text-right pa-0'] .material-icons"));
	}

	public void clickOnPreAdmissionButtonInLeftMenu() {
		clickElement(By.cssSelector("[data-cy='lb-practice-nav-drawer-pre-admission'] .material-icons"));
		// waitForUrl(100,"
		// https://qa.logbox.co.za/premium/#/pre-admission/?context=pre-admissions");
	}

//	public void clickOnPreAdmissionInEllipseMenu() {
//		clickElement(By.cssSelector("[role] .theme--light:nth-of-type(2) .v-list-item__title"));
//	}

	public void clickOnListOptionInEllipseMenu(String listoption) {
		driver.findElement(By.className("v-menu__content theme--light menuable__content__active"));

		List<WebElement> listOptions = driver
				.findElements(By.className("v-menu__content theme--light menuable__content__active"));
		System.out.println(listOptions.size());

		for (int i = 0; i <= listOptions.size() - 1; i++) {
			System.out.println(listOptions.get(i).getText());

			if (listOptions.get(i).getText().equals(listoption)) {

				listOptions.get(i).click();
				break;
			}
		}
	}

	public void clickRefreshButton() {
		clickElement(By.cssSelector(".order-first .material-icons"));
	}

	// Method: Click on MDT Meeting in Quick Links Bar
	public void clickMDTMeetings() {
		waitForElement(100, (By.xpath(
				"//div[@id='app']/div[@class='v-application--wrap']/div[1]/nav//a[@href='#/mdt-meeting']//i[.='groups']")));
		clickElement(By.xpath(
				"//div[@id='app']/div[@class='v-application--wrap']/div[1]/nav//a[@href='#/mdt-meeting']//i[.='groups']"));
	}
}
