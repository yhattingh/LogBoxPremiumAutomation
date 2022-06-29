package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsMessagePatientPage extends BasePageFrameWork {
	
	public String getMessagePatientPageTitle() {
		String messagePatientTitleText =  getElementText(By.cssSelector(".v-toolbar--flat .v-toolbar__title"));
		return messagePatientTitleText;
	}
	
	public void clickSearchOnMessagePatientPage() {
		clickElement(By.cssSelector("[type='text']"));
	}
	
	public void enterPatientNameInSearchFieldOnMessagePatientPage(String patientName) throws InterruptedException {
		enterText(By.cssSelector("[type='text']"),patientName);
	}

}
