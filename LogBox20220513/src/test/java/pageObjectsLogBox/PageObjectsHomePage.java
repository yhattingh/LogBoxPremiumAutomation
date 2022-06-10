package pageObjectsLogBox;

import java.util.List;

import org.apache.poi.hssf.record.RefreshAllRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;

public class PageObjectsHomePage extends BasePageFrameWork {

	public void searchPracticePatientsOnHomePage(String patientname) throws InterruptedException {
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);
		waitForElement(100, (By.cssSelector(".pa-0.text-right  .v-btn__content")));

	}

	public void clickOnSearchedPatient() {
		driver.findElement(By.cssSelector(".text-start")).click();
		// [data-cy] tr td:nth-of-type(2)

	}

	public void clickOnEllipseNextToPatientName() {
		waitForElement(100, (By.cssSelector(".pa-0.text-right  .v-btn__content")));
		driver.findElement(By.cssSelector(".pa-0.text-right  .v-btn__content")).click();
		waitForElement(100, (By.cssSelector("[role] .v-sheet")));

	}

	public void clickOnListOptionInEllipseMenu(String listoption) {
		List<WebElement> listOptions = driver.findElements(By.cssSelector("[role] .v-sheet']"));
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

}
