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
	
	public String getTextFromMessagePatientSearchList() {
		String textPatientListMessageSearch = getElementText(By.cssSelector("div[role='option']  .v-list-item__title"));
		return textPatientListMessageSearch;
	}
	
	public String getTextFromMessageDraftPatientError() {
		String textDraftPatientListMessageSearch = getElementText(By.cssSelector(".grey--text.v-list-item__title"));
		return textDraftPatientListMessageSearch;
	}
	
	public void clickOnReturnedPatient() {
		clickElement(By.cssSelector("div[role='option']  .v-list-item__title"));
	}
	
	public void clickOnSmsRadioButton() {
		clickElement(By.cssSelector("div[role='list'] > div:nth-of-type(1) .v-input__slot > div[role='radiogroup'] .v-input--selection-controls__ripple"));
	}
	
	public void clickOnSmsExpandIcon() {
		clickElement(By.cssSelector(".v-expansion-panel:nth-of-type(1) .material-icons"));
	}
	
	public void enterTextInSmsTextArea(String message) throws InterruptedException {
		enterText(By.cssSelector("textarea[name='message']"), message);
	}

	public void clickSendMessageButton() {
		clickElement(By.cssSelector("[color] .v-btn__content"));
	}
	
	public String getSentMessageSuccess() {
		String successMessage = getElementText(By.cssSelector(".v-dialog .v-toolbar__title"));
		return successMessage;
	}
}
