package pageObjectsLogBox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;

public class PageObjectsHomePage extends BasePageFrameWork {

	public void searchPracticePatientsOnHomePage(String patientname) {
		enterText(By.cssSelector("[aria-label='Search Practice Patients']"), patientname);

	}

	public void clickOnSearchedPatient() {
		driver.findElement(By.cssSelector("[data-cy] tr td:nth-of-type(2)")).click();
		waitForElement(100, (By.cssSelector(".btn-table-row .material-icons")));

	}
}
