package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsPreAdmissionPage extends BasePageFrameWork {

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

	public String getPreAdmissionTitle() {
		String preAdmissionTitle = getElementText(By.cssSelector(" .col .v-toolbar__title"));
		return preAdmissionTitle;
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

	public String getAdmissionDetailsHeader() {
		String admissionHeaderText = getElementText(By.xpath("//div[contains(text(),'Admission Details')]"));
		return admissionHeaderText;
	}

	public void clickSaveButton() throws InterruptedException {
		clickElement(By.cssSelector(".v-btn--has-bg"));
		//waitUntilElementNoLongerDisplays(150, (By.cssSelector(".success .v-snack__content")));
	}

	public void clickCloseButton() {
		clickElement(By.xpath("//*[contains(text(),'Close')]"));
	}

	public void enterSpecialInstructionsToPatient(String instructions) throws InterruptedException {
		enterText(By.cssSelector("[placeholder]"), instructions);
	}

	public void clickOnAddICD10CodesButton() throws InterruptedException {
		clickElement(By.cssSelector(".theme--dark.primary--text .v-btn__content"));
		// waitForElement(100, (By.cssSelector("[type='text']")));
	}

	public String getPatientNameInPreAdmissionList() {
		String patientName = getElementText(By.xpath(".pointer > td:nth-of-type(1)"));
		return patientName;
	}

	public void deletePreAdmission() throws InterruptedException {
		waitForElement(100, (By.cssSelector(
				"tr:nth-of-type(1) > .text-right > div > button:nth-of-type(3)  .material-icons.notranslate.theme--light.v-icon")));
		clickElement(By.xpath(
				"//div[@id='pre-admissions']/div/div[@class='text-center']/div//table/tbody/tr[1]/td[@class='text-right']/div/button[3]//i[.='delete']"));
		clickElement(By.cssSelector(".v-size--default.error--text .v-btn__content"));
		waitUntilElementNoLongerDisplays(100, By.cssSelector(".success .v-snack__content"));
		Thread.sleep(100);
	}

	public void filterOnPatientInPreadmissionList(String patientname) throws InterruptedException {
		waitForElement(100, (By.cssSelector(".v-text-field__slot [type]")));
		clickElement(By.cssSelector(".v-text-field__slot [type]"));
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);
	}

	public String getTextFromSuccessMessage() {
		String successMessage = getElementText(
				By.cssSelector(".success .v-snack__content"));
		return successMessage;
	}

}
