package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsPreAdmissionPage extends BasePageFrameWork {

	public void clickOnPreAdmissionButtonInLeftMenu() {
		clickElement(By.cssSelector("[data-cy='lb-practice-nav-drawer-pre-admission'] .material-icons"));
	}

	public void clickCreatePreAdmissionButton() throws InterruptedException {
		clickElement(By.cssSelector(".btn.btn--router.primary.theme--light.v-btn > .v-btn__content"));
	}

	public void enterPreAdmissionPatientNameToSearch(String patientname) throws InterruptedException {
		enterText(By.cssSelector("[class='py-1 col col-12'] [type='text']"), patientname);
//		Thread.sleep(100);
	}

	public void clickPreAdmissionPatientSearchField() {
		// waitForElement(30,By.cssSelector("[class='py-1 col col-12'] [type='text']"));
		clickElement(By.cssSelector("[class='py-1 col col-12'] [type='text']"));
	}

	public void selectSearchedPatientOnPreAdmission() throws InterruptedException {
		clickElement(By.cssSelector("div[role='option']  .v-list-item__title"));

	}

	public String getTextFromFirstItemOnPreAdmissionSearchList() {
		String textFromPreAdmissionList = getElementText(By.cssSelector("[aria-label='Search for a patient']"));
		return textFromPreAdmissionList;
	}

	public void clickSelectAfterPreAdmissionPatientSearch() {
		clickElement(By.cssSelector("[data-cy='lb-psd-select'] .v-btn__content"));
	}

	public String getTextFromPreAdmissionPatientHeader() {
		String patientName = getElementText(By.cssSelector(".mt-1.title"));
		return patientName;
	}

	public String getTextFromPreAdmissionRequiredFieldsValidationPageMessage() {
		String validationPageMessage = getElementText(By.className("v-snack__content"));
		return validationPageMessage;
	}

	public String getTextfromDraftPatientErrorMessageOnPreadmission() {
		String draftPatientErrorMessage = getElementText(By.className("v-snack__content"));
		return draftPatientErrorMessage;
	}

	public String getTextFromPreAdmissionRequiredFieldsValidationErrorText() {
		String validationErrorText = getElementText(
				By.xpath("//div[contains(text(),'The Expected Date of Admission is required.')]"));
		return validationErrorText;
	}

	public void clickSaveButton() throws InterruptedException {
		clickElement(By.cssSelector(".v-btn--has-bg"));
	}

	public void enterSpecialInstructionsToPatient(String instructions) throws InterruptedException {
		enterText(By.cssSelector("[placeholder]"), instructions);
	}
	
	public void enterAndSelectHospitalName(String hospitalName) throws InterruptedException {
		clickElement(By.cssSelector("[data-cy='lb-hospital-search']"));
		enterText(By.cssSelector("[data-cy='lb-hospital-search']"), hospitalName);
		clickElement(By.className("div[role='option']  .v-list-item__title"));
	}
	
	public void clickOnICD10Code() throws InterruptedException {
		clickElement(By.cssSelector(".theme--dark.primary--text .v-btn__content"));
		waitForElement(100, (By.cssSelector(".keepWidth.theme--light.v-card.v-sheet .v-toolbar__title")));
	}
	
	public void clickAndEnterICD10CodeSearchInDialog(String icd10code) throws InterruptedException {
		clickElement(By.cssSelector(".keepWidth.theme--light.v-card.v-sheet .v-input__slot"));
		enterText(By.cssSelector("Malignant neoplasms follow-up"), icd10code);
		clickElement(By.cssSelector(".description-content.v-list-item__title"));
		clickElement(By.cssSelector(".primary--text.theme--light.v-btn.v-btn--text.v-size--default > .v-btn__content"));
	}

//	public void clickOneDatePickerFieldPreAdmission() {
//		clickElement(By.cssSelector("[data-cy='date-picker']"));
//		waitForElement(100, (By.cssSelector(".v-date-picker-title")));
//
//	}

}
