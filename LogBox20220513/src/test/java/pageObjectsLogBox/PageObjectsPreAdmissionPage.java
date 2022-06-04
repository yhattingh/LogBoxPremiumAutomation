package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsPreAdmissionPage extends BasePageFrameWork {

	public void clickOnPreAdmissionButtonInLeftMenu() {
		clickElement(By.cssSelector("[data-cy='lb-practice-nav-drawer-pre-admission'] .material-icons"));
	}

	public void clickCreatePreAdmissionButton() {
		clickElement(By.cssSelector(".btn.btn--router.primary.theme--light.v-btn > .v-btn__content"));
	}

	public void enterPreAdmissionPatientNameToSearch(String patientname) {
		enterText(By.cssSelector("[aria-label='Search for a patient']"), patientname);
	}

	public void selectSearchedPatientOnPreAdmission() throws InterruptedException {
		clickElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div/a/div/div"));

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
	
	

}
