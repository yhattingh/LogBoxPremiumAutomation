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
		waitForElement(50, (By.cssSelector(".v-size--small .theme--light")));
		clickElement(By.cssSelector(".v-size--small .theme--light"));

	}

	public void selectOptionFromMoreButtonList(String optiontext) {
		clickElement(By.xpath("//*[contains(text(),'" + optiontext + "')]"));
	}

	public void inputCaseFileProblem(String presentingproblem) throws InterruptedException {
		enterText(By.cssSelector("input[name='Presenting Problem']"), presentingproblem);
	}

	public void clickSaveButtonCaseFile() {
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

	public void selectAnItemInDropdownList(String caseFileName) {

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


	public void selectItemFromDropdownList(String caseFileName) {

		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + caseFileName + "')]"));

		clickElement(By.xpath("//*[contains(text(),'" + caseFileName + "')]"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		System.out.println("Which case file was selected" + " " + getTextFromFirstItemOnCaseFilesList());

	}
	
		//https://www.tutorialspoint.com/scroll-element-into-view-with-selenium


	
	public void clickAddActivityButton() {
		clickElement(By.cssSelector(".mx-0.primary--text.theme--light.v-btn.v-btn--outlined.v-size--small > .v-btn__content"));
	}
	
	public void addTextNote(String enterTextNote) throws InterruptedException {
		enterText(By.cssSelector("[rows]"), enterTextNote);
	}
	
	public void clickPostButton() {
		clickElement(By.cssSelector("button#add-activity-submit-btn-id > .v-btn__content"));
	}
	
	public String getTextFromActivityNote() throws InterruptedException {
		waitForElement(1500, By.cssSelector("div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet > .no-gutters.row > .v-card__text  .col.pr-3"));
		String textFromActivityNote = getElementText(By.cssSelector("div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet > .no-gutters.row > .v-card__text  .col.pr-3"));
		return textFromActivityNote;
	}
}


