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

	public void searchPracticePatientsOnHomePage(String patientname) throws InterruptedException {
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);
		waitUntilElementNoLongerDisplays(10,By.cssSelector("tbody .pointer:nth-of-type(2) .text-left:nth-of-type(4)"));
	}

	public void clickOnSearchedPatient() {
		//waitForElement(100, (By.cssSelector("tbody .pointer:nth-of-type(1) .text-start")));
		waitUntilElementNoLongerDisplays(50, By.className(".pageLoadingBar"));
		driver.findElement(By.cssSelector("tbody .pointer:nth-of-type(1) .text-start")).click();
		// [data-cy] tr td:nth-of-type(2)
	}
	
	public void clickOnEllipseNextToPatientName() {
		driver.findElement(By.cssSelector("[class='text-right pa-0'] .material-icons")).click();
	}
	
	public void clickOnPreAdmissionInEllipseMenu() {
		clickElement(By.cssSelector("[role] .theme--light:nth-of-type(2) .v-list-item__title"));
	}

	public void clickOnListOptionInEllipseMenu(String listoption) {
		driver.findElement(By.className("v-menu__content theme--light menuable__content__active"));
		
		List<WebElement> listOptions = driver.findElements(By.className("v-menu__content theme--light menuable__content__active"));
		System.out.println(listOptions.size());

		for (int i = 0; i <= listOptions.size() - 1; i++) {
			System.out.println(listOptions.get(i).getText());

			if (listOptions.get(i).getText().equals(listoption)) {

				listOptions.get(i).click();
				break;
			}
		}
	}

	public void clickInSearchPracticePatient() {
		clickElement(By.cssSelector(".v-text-field__slot [type]"));
	}
	
	public void clickRefreshButton() {
		clickElement(By.cssSelector(".order-first .material-icons"));
	}

}
