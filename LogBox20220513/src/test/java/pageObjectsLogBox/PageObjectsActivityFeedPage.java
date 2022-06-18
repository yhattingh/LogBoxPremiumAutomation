package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityFeedPage extends BasePageFrameWork {

	// Method: Click Search Activities
	public void searchForActivity(String searchedactivity) throws InterruptedException {
		waitForElement(100, By.cssSelector("input"));
		enterText(By.cssSelector("input"), searchedactivity);
	}

	// Method: Click Activity on Activity Feed Page
	public void clickActivity() {
		waitUntilElementNoLongerDisplays(100, By.className(".pageLoadingBar"));
		clickElement(By.cssSelector("div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet"));
	}

	// Method: Click on Activity to Edit
	public void clickToEditActivity() {
		waitUntilElementNoLongerDisplays(100, By.className(".pageLoadingBar"));
		clickElement(By.cssSelector("[class] [class='col col-12']:nth-of-type(3) [class] .small-flex-child:nth-of-type(1)"));
	}

	// Method: Click DateTime
	public void clickDateTimeButton() {
		clickElement(By.cssSelector(
				"div:nth-of-type(1) > .activity-meta-button.theme--light.v-btn.v-btn--block.v-btn--has-bg.v-size--small  .activity-meta-button-text"));
	}

	// Method: Click on Date in Date Dialog Box
	public void clickInDateField() {
		clickElement(By.cssSelector("[data-cy='date-picker']"));
	}

	// Method: Click Inpatient/Outpatient button
	public void clickInpatientOutpatientButton() {
		waitUntilElementNoLongerDisplays(100, By.className(".pageLoadingBar"));
		clickElement(By.xpath("//*[contains(text(),'Inpatient/Outpatient')]"));
	}

	// Method: Verify if radio button is selected
	public void clickInpatientRadioButton() {
		clickElement(By.className(".v-input--radio-group__input .theme--light:nth-of-type(1) .v-input--selection-controls__ripple"));
	}
	
	public void selectInpatientRadioButton() throws InterruptedException {
		clickElement(By.xpath("//*[contains(text(),'INPATIENT')]"));
	}
	
	public void clickSetButtonOnLocationDialogBox() {
		clickElement(By.xpath("//*[contains(text(),'Set')]"));
	}	
}
