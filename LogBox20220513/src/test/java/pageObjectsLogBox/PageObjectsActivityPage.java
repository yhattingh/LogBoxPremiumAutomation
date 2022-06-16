package pageObjectsLogBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hamcrest.core.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityPage extends BasePageFrameWork {

	public void clickMoreButton() {
		// waitForElement(50, (By.cssSelector(".v-size--small .theme--light")));
		clickElement(By.cssSelector(".v-size--small .theme--light"));

	}

	public void selectOptionFromMoreButtonList(String optiontext) {
		clickElement(By.xpath("//*[contains(text(),'" + optiontext + "')]"));
	}

	public void inputCaseFileProblem(String presentingproblem) throws InterruptedException {
		enterText(By.cssSelector("input[name='Presenting Problem']"), presentingproblem);
	}

	public void clickCaseFileDialogSaveButton() {
		clickElement(By.cssSelector(".v-btn--has-bg.theme--light"));
	}

	public void clickCaseFilesDropdown() throws InterruptedException {
		clickElement(By.cssSelector(".justify-space-between.row"));
	}

	public String getTextFromFirstItemOnCaseFilesList() {
		waitUntilElementNoLongerDisplays(50, By.cssSelector(".success .v-snack__content"));
		String textFromCaseFilesList = getElementText(By.cssSelector(".black--text"));
		return textFromCaseFilesList;
	}

	public void selectCaseFileFromDropdownList(String caseFileName) {

		// Click on Dropdown
		driver.findElement(By.className("v-select__selections"));

		List<WebElement> allOptions = driver.findElements(By.className("selectCaseFileListItem"));
		System.out.println("number of returned rows: " + " " + allOptions.size());

		for (int i = 0; i <= allOptions.size() - 1; i++) {
			System.out.println(allOptions.get(i).getText());

			if (allOptions.get(i).getText().contains(caseFileName)) {
				allOptions.get(i).click();
				break;
			}
		}
	}

	//Method:  Select a Case File from Dropdown list
	public void selectItemFromDropdownList(String caseFileName) throws InterruptedException {
		
		WebElement element = driver.findElement(By.cssSelector(".justify-space-between.row"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
		clickElement(By.xpath("//*[contains(text(),'" + caseFileName + "')]"));

		System.out.println("Which case file was selected" + " " + getTextFromFirstItemOnCaseFilesList());
	}

	//Method:  Click Add Activity Button
	public void clickAddActivityButton() {
		clickElement(By.cssSelector(".mx-0.primary--text.theme--light.v-btn.v-btn--outlined.v-size--small > .v-btn__content"));
	}

	//Method:  Enter Text in Activity Dialog Box
	public void addTextNote(String enterTextNote) throws InterruptedException {
		enterText(By.cssSelector("[rows]"), enterTextNote);
	}

	//Method:  Click on the Post Button in the Activity Dialog Box
	public void clickPostButton() {
		clickElement(By.cssSelector("button#add-activity-submit-btn-id > .v-btn__content"));
	}

	public String getTextFromActivityNote() throws InterruptedException {
		waitForElement(1500, By.cssSelector(
				"div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet > .no-gutters.row > .v-card__text  .col.pr-3"));
		String textFromActivityNote = getElementText(By.cssSelector(
				"div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet > .no-gutters.row > .v-card__text  .col.pr-3"));
		return textFromActivityNote;
	}

	public void waitForCaseFileCreatedSnackContentToNoLongerDisplay() {
		waitUntilElementNoLongerDisplays(100, By.cssSelector(".success .v-snack__content"));
	}

	public void searchPracticePatientsOnHomePage(String patientname) throws InterruptedException {
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);
	}

	public void selectCategory(String categorytext) {
		clickElement(By.xpath("//*[contains(text(),'" + categorytext + "')]"));
	}
	
	// Method:  Click on the Diagnosis Action Icon at Top of the Page
	public void clickDiagnosisActionIcon() {
		clickElement(By.cssSelector(".row > button:nth-of-type(3)"));
	}

	public void searchForICD10Code(String icd10Code) throws InterruptedException {
		enterText(By.cssSelector("[type='text']"), icd10Code);
	}

	public void selectICD10Code(String selectICD10Code) {
		clickElement(By.xpath("//*[contains(text(),'" + selectICD10Code + "')]"));
	}

	public void clickAddButtonOnICD10CodeDialog() {
		clickElement(By.cssSelector(".primary--text.theme--light.v-btn.v-btn--text.v-size--default > .v-btn__content"));
	}

	public String getTextForSelectedDiagnosisCodes() {
		String textFromSelectedDiagnosisCodes = getElementText(By.cssSelector("[class='row mt-1'] .v-list"));
		return textFromSelectedDiagnosisCodes;
	}

	public void removeICD10Code(String selectICD10Code) {
		waitForElement(100, By.xpath("//div[@role='list']/div[1]/div[3]/button[@type='button']//i[.='close']"));
		clickElement(By.xpath("//div[@role='list']/div[1]/div[3]/button[@type='button']//i[.='close']"));
	}

	public void clickRemoveButton(String selectICD10Code) {

		// driver.findElement(By.className("v-select__selections"));

		List<WebElement> selectedICD10Codes = driver.findElements(By.cssSelector("[class='row mt-1'] .v-list"));
		System.out.println("number of returned rows: " + " " + selectedICD10Codes.size());

		for (int i = 0; i <= selectedICD10Codes.size() - 1; i++) {
			System.out.println(selectedICD10Codes.get(i).getText());

			if (selectedICD10Codes.get(i).getText().contains(selectICD10Code)) {
				clickElement(By.cssSelector("div:nth-of-type(1)  .theme--light.v-btn.v-btn--icon.v-btn--round.v-size--small  .material-icons.notranslate.theme--light.v-icon"));
				//selectedICD10Codes.get(i).click();
				break;
			}
		}
	}
	
	public void clickActivityPostButton() {
		clickElement(By.cssSelector("button#add-activity-submit-btn-id > .v-btn__content"));
	}
	
	//Method:  Click ActivityFeed to Update Activity
	public void clickActivityFeedToUpdateActivity() {
		clickElement(By.cssSelector(".col.col-12.text-start > span:nth-of-type(1)"));
	}
}
