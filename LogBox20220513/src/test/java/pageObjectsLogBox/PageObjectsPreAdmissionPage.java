package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsPreAdmissionPage extends BasePageFrameWork {

	public void clickOnPreAdmissionButtonInLeftMenu() {
		clickElement(By.cssSelector("[data-cy='lb-practice-nav-drawer-pre-admission'] .material-icons"));
	}

	public void clickCreatePreAdmissionButton() throws InterruptedException {
		clickElement(By.cssSelector(".btn.btn--router.primary.theme--light.v-btn > .v-btn__content"));
		Thread.sleep(100);
	}

	public void enterPreAdmissionPatientNameToSearch(String patientname) throws InterruptedException {
		enterText(By.cssSelector("[class='py-1 col col-12'] [type='text']"), patientname);
		Thread.sleep(100);
	}
	public void clickPreAdmissionPatientSearchField() {
		clickElement(By.cssSelector("[class='py-1 col col-12'] [type='text']"));
	}

	public void selectSearchedPatientOnPreAdmission() throws InterruptedException {
		clickElement(By.xpath("div[role='listbox'] > div:nth-of-type(1)  .v-list-item__title"));

	}

	public String getTextFromFirstItemOnPreAdmissionSearchList() {
		String textFromCaseFilesList = getElementText(By.cssSelector("[aria-label='Search for a patient']"));
		return textFromCaseFilesList;
	}

	public void clickSelectAfterPreAdmissionPatientSearch() {
		clickElement(By.cssSelector(
				"div:nth-of-type(1) > .v-stepper__wrapper > .v-card__actions > .primary--text.theme--light.v-btn.v-btn--flat > .v-btn__content"));
	}
	
	public String getTextFromPreAdmissionPatientHeader() {
		String patientName = getElementText(By.cssSelector(".layout > .flex.xs5"));
		return patientName;
	}
	
	public String getTextFromPreAdmissionRequiredFieldsValidationPageMessage() {
		String validationPageMessage = getElementText(By.cssSelector(".error .v-snack__content"));
		return validationPageMessage;
	}
	
	public String getTextFromPreAdmissionRequiredFieldsValidationErrorText() {
		String validationErrorText = getElementText(By.xpath("//div[contains(text(),'The Expected Date of Admission is required.')]"));
		return validationErrorText;
	}
	
	public void clickSaveButton() throws InterruptedException {
		clickElement(By.cssSelector(".v-btn--has-bg .v-btn__content"));
		wait(10);
	}
	

}
