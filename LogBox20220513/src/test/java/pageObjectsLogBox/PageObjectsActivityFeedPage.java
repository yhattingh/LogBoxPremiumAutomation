package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityFeedPage extends BasePageFrameWork {

	// Method: Click Search Activities
	public void searchForActivity(String searchedactivity) throws InterruptedException {
		waitForElement(50, By.cssSelector("input"));
		enterText(By.cssSelector("input"), searchedactivity);
	}

	// Method: Click Activity on Activity Feed Page
	public void clickSearchedActivity(String searchedactivity) {
		String returnedActivity = getElementText(
				By.cssSelector("[class='col col-12']:nth-of-type(1) span:nth-of-type(2)"));
		if (returnedActivity.contains(searchedactivity)) {
			clickElement(By.cssSelector("div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet"));
		} else {
			waitUntilElementNoLongerDisplays(5, By.className(".pageLoadingBar"));
			clickElement(By.cssSelector("div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet"));
		}
	}

	// Method: Click on Activity to Edit
	public void clickToEditActivity() {
		String returnedActivityFeed = getElementText(
				By.cssSelector("[class] [class='col col-12']:nth-of-type(1) [class] .small-flex-child:nth-of-type(1)"));
		if (returnedActivityFeed.contains("ActivityFeed")) {
			clickElement(By.cssSelector("[class] [class='col col-12']:nth-of-type(1) [class] .small-flex-child:nth-of-type(1)"));
		} else {
			waitUntilElementNoLongerDisplays(5, By.className(".pageLoadingBar"));
			clickElement(By.cssSelector("[class] [class='col col-12']:nth-of-type(1) [class] .small-flex-child:nth-of-type(1)"));
		}
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
		waitforClick(200, By.cssSelector("[class='col-md-9 col-12'] .v-btn__content"));
		clickElement(By.xpath("//*[contains(text(),'Inpatient/Outpatient')]"));
	}

	// Method: Verify if radio button is selected
	public void clickInpatientRadioButton() {
		clickElement(By.cssSelector(".v-input--radio-group__input .theme--light:nth-of-type(1) .v-input--selection-controls__ripple"));
	}
	
	public void selectInpatientRadioButton() throws InterruptedException {
		clickElement(By.xpath("//*[contains(text(),'INPATIENT')]"));
	}
	
	public void clickSetButtonOnLocationDialogBox() {
		clickElement(By.xpath("//*[contains(text(),'Set')]"));
	}	
	
	// Method: Get Text of Inpatient / Outpatient button
	public String getTextForInOutPatient() {
		String InOutPatient = getElementText(By.xpath("//div[@id='app']/div[@class='v-application--wrap']/div[1]/main[@class='v-main']"
				+ "/div[@class='v-main__wrap']/div[@class='container container--fluid']/div/div[2]/div/div[2]/div[@class='row']/div[1]/div[1]/button[@type='button']//span[@class='activity-meta-button-text']"));
		return InOutPatient;
	}
}
