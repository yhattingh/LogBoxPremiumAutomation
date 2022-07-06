package pageObjectsLogBox;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	
	public void clickOnQuestionnaireExpandButton() {
		clickElement(By.cssSelector("div:nth-of-type(2) > .v-expansion-panel-header  .material-icons.notranslate.theme--light.v-icon"));
	}
	
	public void clickOnFormsExpandButton() {
		clickElement(By.cssSelector("div:nth-of-type(3) > .v-expansion-panel-header  .material-icons.notranslate.theme--light.v-icon"));
	}
	
	public void clickOnAppointmentReminderExpandButton() {
		clickElement(By.cssSelector("div:nth-of-type(4) > .v-expansion-panel-header  .material-icons.notranslate.theme--light.v-icon"));
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
	
	public Boolean isMessageOptionDisplaying() {
		Boolean messageDisplaying = elementExists(By.cssSelector("div:nth-of-type(1) > .v-expansion-panel-header"));
		return messageDisplaying;
	}
	
	public Boolean isQuestionnaireOptionDisplaying() {
		Boolean questionDisplaying = elementExists(By.cssSelector("div:nth-of-type(2) > .v-expansion-panel-header"));
		return questionDisplaying;
	}
	
	public Boolean isFormOptionDisplaying() {
		Boolean formDisplaying = elementExists(By.cssSelector("div:nth-of-type(3) > .v-expansion-panel-header"));
		return formDisplaying;
	}
	
	public Boolean isAppointmentDisplaying() {
		Boolean appointDisplaying = elementExists(By.cssSelector("div:nth-of-type(4) > .v-expansion-panel-header"));
		return appointDisplaying;
	}
	
	public void selectQuestionnaire() {
		clickElement(By.cssSelector("div[role='list'] > div:nth-of-type(2) input[role='checkbox']"));
	}

	public void selectForm() {
		clickElement(By.cssSelector(".v-expansion-panel-content div[role='list'] .v-input__slot > div[role='radiogroup'] > div:nth-of-type(1) .v-input--selection-controls__ripple"));	
	}
	
	public void selectDateAndTimeForAppointmentReminder(String Date, String Time) {
		
	}
	
	
}
