package pageObjectsLogBox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;

public class PageObjectsHomePage extends BasePageFrameWork {
	
	public void searchPracticePatientsOnHomePage(String patientname) {
		WebElement searchPatientField = driver.findElement(By.cssSelector("[aria-label='Search Practice Patients']"));
		searchPatientField.sendKeys(patientname);
		searchPatientField.click();
	}
	
	public void clickOnSearchedPatient() {
		driver.findElement(By.cssSelector("[data-cy] tr td:nth-of-type(2)")).click();
		
	}

}
