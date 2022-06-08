package pageObjectsLogBox;

import org.apache.poi.hssf.record.RefreshAllRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;

public class PageObjectsHomePage extends BasePageFrameWork {

	public void searchPracticePatientsOnHomePage(String patientname) throws InterruptedException {
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);

	}

	public void clickOnSearchedPatient() {
		driver.findElement(By.cssSelector("[data-cy] tr td:nth-of-type(2)")).click();
		waitForElement(100, (By.cssSelector(".btn-table-row .material-icons")));

	}
	
	public void clickInSearchPracticePatient() {
		clickElement(By.cssSelector(".v-text-field__slot [type]"));
	}

}
