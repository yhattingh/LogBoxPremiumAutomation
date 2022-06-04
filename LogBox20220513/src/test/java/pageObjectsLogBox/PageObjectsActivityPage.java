package pageObjectsLogBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityPage extends BasePageFrameWork {

	public void clickMoreButton() {
		clickElement(By.cssSelector(".btn-table-row .material-icons"));
	}

	public void selectOptionFromMoreButtonList(String optiontext) {
		clickElement(By.linkText(optiontext));
	}

	public void inputCaseFileProblem(String presentingproblem) {
		enterText(By.cssSelector("input[name='Presenting Problem']"), presentingproblem);
	}

	public void clickSaveButtonCaseFile() {
		clickElement(
				By.cssSelector("div:nth-of-type(11) .primary.theme--light.v-btn.v-btn--depressed > .v-btn__content"));
	}

	public void clickCaseFilesDropdown() {
		clickElement(By.cssSelector(".container.fluid div[role='combobox'] .material-icons.theme--light.v-icon"));
	}

	public String getTextFromFirstItemOnCaseFilesList() {
		String textFromCaseFilesList = getElementText(
				By.cssSelector("#app .selectCaseFileListItem:nth-of-type(1) .v-list__tile__title"));
		return textFromCaseFilesList;
	}

	public String getTextFromSecondItemOnCaseFilesList() {
		String textFromCaseFilesList = getElementText(
				By.cssSelector("#app .selectCaseFileListItem:nth-of-type(2) .v-list__tile__title"));
		return textFromCaseFilesList;
	}

}
