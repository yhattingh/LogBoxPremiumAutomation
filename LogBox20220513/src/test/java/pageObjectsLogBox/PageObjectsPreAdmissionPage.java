package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsPreAdmissionPage extends BasePageFrameWork {

	// Method: To Click the Create Pre-Admissions Button
	public void clickCreatePreAdmissionButton() throws InterruptedException {
		clickElement(By.cssSelector(".btn.btn--router.primary.theme--light.v-btn > .v-btn__content"));
	}

	// Method: To Click the Search Field on Pre-Admissions
	public void clickPreAdmissionPatientSearchField() {
		clickElement(By.cssSelector("[class='py-1 col col-12'] [type='text']"));
	}

	// Method: To enter Patient Name in Pre-Admission Search Dialog
	public void enterPreAdmissionPatientNameToSearch(String patientname) throws InterruptedException {
		enterText(By.cssSelector("[class='py-1 col col-12'] [type='text']"), patientname);
	}

	// Method: To select the Searched Patient on Pre-Admission Dialog
	public void selectSearchedPatientOnPreAdmission() throws InterruptedException {
		clickElement(By.cssSelector("div[role='option']  .v-list-item__title"));
	}

	// Method: To get the Text from the First Item of the Pre-Admissions Search List
	public String getTextFromFirstItemOnPreAdmissionSearchList() {
		String textFromPreAdmissionList = getElementText(By.cssSelector("[aria-label='Search for a patient']"));
		return textFromPreAdmissionList;
	}

	// Method: To click the Select Button after Searched for Patient
	public void clickSelectAfterPreAdmissionPatientSearch() {
		clickElement(By.cssSelector("[data-cy='lb-psd-select'] .v-btn__content"));
	}

	// Method: To get text from the Pre-Admission Patient Header
	public String getTextFromPreAdmissionPatientHeader() {
		String patientName = getElementText(By.cssSelector(".mt-1.title"));
		return patientName;
	}

	// Method: Get the Pre-Admission Title
	public String getPreAdmissionTitle() {
		String preAdmissionTitle = getElementText(By.cssSelector(" .col .v-toolbar__title"));
		return preAdmissionTitle;
	}

	// Method: Get text from Pre-Admission page validation Message
	public String getTextFromPreAdmissionRequiredFieldsValidationPageMessage() {
		String validationPageMessage = getElementText(By.className("v-snack__content"));
		return validationPageMessage;
	}

	// Method: To get the text from the Error Message on a Draft Patient
	public String getTextfromDraftPatientErrorMessageOnPreadmission() {
		String draftPatientErrorMessage = getElementText(By.className("v-snack__content"));
		return draftPatientErrorMessage;
	}

	// Method: To get text from a Field Validation error
	public String getTextFromPreAdmissionRequiredFieldsValidationErrorText() {
		String validationErrorText = getElementText(
				By.xpath("//div[contains(text(),'The Expected Date of Admission is required.')]"));
		return validationErrorText;
	}

	// Method: To get the text from the First Row a the Pre-Admission Table
	public String getTextFromTableFirstRow() {
		String firstRowText = getElementText(By.cssSelector("[colspan]"));
		return firstRowText;
	}

	// Method: To get the text from the Admission Details header
	public String getAdmissionDetailsHeader() {
		String admissionHeaderText = getElementText(By.xpath("//div[contains(text(),'Admission Details')]"));
		return admissionHeaderText;
	}

	// Method: To click the Save Button when creating a Pre-Admission
	public void clickSaveButton() throws InterruptedException {
		clickElement(By.cssSelector(".v-btn--has-bg"));
	}

	// Method: To click the Close Button After the Pre-Admission was created
	public void clickCloseButton() {
		clickElement(By.xpath("//*[contains(text(),'Close')]"));
	}

	// Method: To enter Text Note in Special Instructions field on Pre-Admission
	public void enterSpecialInstructionsToPatient(String instructions) throws InterruptedException {
		enterText(By.cssSelector("[placeholder]"), instructions);
	}

	// Method: To click the Add Codes Button on Pre-Admissions
	public void clickOnAddICD10CodesButton() throws InterruptedException {
		clickElement(By.cssSelector(".theme--dark.primary--text .v-btn__content"));
	}

	// Method: To get the Patient Name in the Pre-Admission List
	public String getPatientNameInPreAdmissionList() {
		String patientName = getElementText(By.xpath(".pointer > td:nth-of-type(1)"));
		return patientName;
	}

	// Method: To click the Delete Pre-Admission Action Icon Next to Pre-Admission
	// row
	public void deletePreAdmission() throws InterruptedException {
		waitForElement(100, (By.cssSelector(
				"tr:nth-of-type(1) > .text-right > div > button:nth-of-type(3)  .material-icons.notranslate.theme--light.v-icon")));
		clickElement(By.xpath(
				"//div[@id='pre-admissions']/div/div[@class='text-center']/div//table/tbody/tr[1]/td[@class='text-right']/div/button[3]//i[.='delete']"));
	}

	// Method: To confirm the Pre-Admission Delete
	public void confirmPreAdmissionDelete() throws InterruptedException {
		clickElement(By.cssSelector(".v-size--default.error--text .v-btn__content"));
		waitUntilElementNoLongerDisplays(200, By.cssSelector(".success .v-snack__content"));
	}

	// Method: To filter on a Patient Name in Existing Pre-Admissions
	public void filterOnPatientInPreadmissionList(String patientname) throws InterruptedException {
		waitForElement(100, (By.cssSelector(".v-text-field__slot [type]")));
		clickElement(By.cssSelector(".v-text-field__slot [type]"));
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);
	}

	// Method: To get text after successful Pre-Admission Delete
	public String getTextFromSuccessMessage() {
		String successMessage = getElementText(By.cssSelector(".success .v-snack__content"));
		return successMessage;
	}

	// Method: To wait for the Pre-Admission Table to be Empty
	public void waitForTableToBeEmpty() {
		Boolean isTableEmpty = checkIfTableIsEmpty(By.cssSelector("table"), By.cssSelector("table > tbody"));
		if (isTableEmpty.equals(true)) {
			System.out.println("Table is empty");
		} else {
			System.out.println("Table is not empty");
			waitUntilElementNoLongerDisplays(10, By.className(".pageLoadingBar"));
		}
	}
}
