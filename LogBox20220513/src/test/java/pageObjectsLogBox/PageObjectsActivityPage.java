package pageObjectsLogBox;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityPage extends BasePageFrameWork {

	// Method: Click on More Button on Activity Feed page
	public void clickMoreButton() {
		clickElement(By.cssSelector(".v-size--small .theme--light"));
	}

	// Method: Select an Option From an Open Dialog Box
	public void selectOptionFromMoreButtonList(String optiontext) {
		clickElement(By.xpath("//*[contains(text(),'" + optiontext + "')]"));
	}

	// Method: Add a Presenting Problem on Case File Dialog Box
	public void inputCaseFilePresentingProblem(String presentingproblem) throws InterruptedException {
		enterText(By.cssSelector("input[name='Presenting Problem']"), presentingproblem);
	}

	// Method: Click SAVE button on Case File Dialog Box
	public void clickCaseFileDialogSaveButton() {
		clickElement(By.cssSelector(".v-btn--has-bg.theme--light"));
	}

	// Method: Click on the Case Files DropDown Box
	public void clickCaseFilesDropdown() throws InterruptedException {
		clickElement(By.cssSelector(".justify-space-between.row"));
	}

	// Method: Get the Text From the First Item in the Case Files DropDown Box
	public String getTextFromFirstItemOnCaseFilesList() {
		waitUntilElementNoLongerDisplays(50, By.cssSelector(".success .v-snack__content"));
		String textFromCaseFilesList = getElementText(By.cssSelector(".black--text"));
		return textFromCaseFilesList;
	}

	// Method: Select a Case File From the Case Files DropDown Box
	public void selectCaseFileFromDropdownList(String caseFileName) {
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

	// Method: Select a Case File From the Case Files DropDown Box Using
	// scrollIntoView method
	public void selectItemFromDropdownList(String caseFileName) throws InterruptedException {
		scrollIntoView(By.cssSelector(".justify-space-between.row"));
		clickElement(By.xpath("//*[contains(text(),'" + caseFileName + "')]"));
		System.out.println("Which case file was selected" + " " + getTextFromFirstItemOnCaseFilesList());
	}

	// Method: Click Add Activity Button
	public void clickAddActivityButton() {
		clickElement(By.cssSelector(".mx-0.primary--text.theme--light.v-btn.v-btn--outlined.v-size--small > .v-btn__content"));
	}

	// Method: Enter Text in Activity Dialog Box
	public void addTextNote(String enterTextNote) throws InterruptedException {
		enterText(By.cssSelector("[rows]"), enterTextNote);
	}

	// Method: Click on the Post Button in the Activity Dialog Box
	public void clickPostButton() {
		clickElement(By.cssSelector("button#add-activity-submit-btn-id > .v-btn__content"));
	}

	// Method: Get Text From the Activity Notes
	public String getTextFromActivityNote() throws InterruptedException {
		waitForElement(200, By.cssSelector(
				"div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet > .no-gutters.row > .v-card__text  .col.pr-3"));
		String textFromActivityNote = getElementText(By.cssSelector(
				"div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet > .no-gutters.row > .v-card__text  .col.pr-3"));
		return textFromActivityNote;
	}

	// Method: Wait for Page Message To No Longer Display
	public void waitForCaseFileCreatedSnackContentToNoLongerDisplay() {
		waitUntilElementNoLongerDisplays(100, By.cssSelector(".success .v-snack__content"));
	}

	// Method: Select the Category for Activity Note
	public void selectCategory(String categorytext) {
		clickElement(By.xpath("//*[contains(text(),'" + categorytext + "')]"));
	}

	// Method: Click on the Diagnosis Action Icon at Top of the Page
	public void clickDiagnosisActionIcon() {
		clickElement(By.cssSelector(".row > button:nth-of-type(3)"));
	}

	// Method: Get Text of Selected/Added Diagnosis Codes / ICD10 Code
	public String getTextForSelectedDiagnosisCodes() {
		String textFromSelectedDiagnosisCodes = getElementText(By.cssSelector("[class='row mt-1'] .v-list"));
		return textFromSelectedDiagnosisCodes;
	}

	// Method: Remove one of the Diagnosis Codes / ICD10 Code
	public void removeICD10Code(String selectICD10Code) {
		waitForElement(100, By.xpath("//div[@role='list']/div[1]/div[3]/button[@type='button']//i[.='close']"));
		clickElement(By.xpath("//div[@role='list']/div[1]/div[3]/button[@type='button']//i[.='close']"));
	}

	// Method: Remove selected Diagnosis Codes / ICD10 Code
	public void clickRemoveButton(String selectICD10Code) {
		List<WebElement> selectedICD10Codes = driver.findElements(By.cssSelector("div[role='list']"));
		for (int i = 0; i <= selectedICD10Codes.size() - 1; i++) {
			System.out.println(selectedICD10Codes.get(i).getText());
			if (selectedICD10Codes.get(i).getText().contains(selectICD10Code)) {
				clickElement(By.cssSelector(
						"div:nth-of-type(1)  .theme--light.v-btn.v-btn--icon.v-btn--round.v-size--small  .material-icons.notranslate.theme--light.v-icon"));
				selectedICD10Codes.get(i).click();
				break;
			}
		}
	}

	// Method: Click the Post Button on the Activity Dialog
	public void clickActivityPostButton() {
		clickElement(By.cssSelector("button#add-activity-submit-btn-id > .v-btn__content"));
	}

	// Method: Click ActivityFeed to Update Activity
	public void clickOnActivityFeedToUpdateActivity() {
		clickElement(By.cssSelector(".col.col-12.text-start > span:nth-of-type(1)"));
	}
	
	// Method: Click Attachment Action Icon
	public void clickAttachmentIcon() {
		clickElement(By.cssSelector(".material-icons.notranslate.primary--text.theme--light.v-icon"));
	}
	
	// Method: Click Here To Upload Option
	public void clickAndUploadFile() throws InterruptedException {
		
//		String filePath = "C:\\autoProgram\\LogBoxPremiumAutomation\\LogBox20220513\\resources\\testDataInput\\UploadAttachmentTest.docx";
		
		String filePath = "C:\\Users\\Yolande\\Downloads\\UploadAttachmentTest.docx";
		
		String uploadFileButton = ".d-block.d-md-flex.justify-center.my-10.my-md-2.theme--light.v-subheader > div > .primary--text";
		
		clickElement(By.cssSelector(uploadFileButton));
		
		WebElement uploadFile = driver.findElement(By.cssSelector(uploadFileButton));
		uploadFile.sendKeys(filePath);
	}
}
