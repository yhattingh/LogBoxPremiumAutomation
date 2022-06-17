package pageObjectsLogBox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public void clickInpatientButton() {
		waitForElement(100, (By.cssSelector("[class='col-md-9 col-12'] .activity-meta-button-text")));
		clickElement(By.cssSelector("[class='col-md-9 col-12'] .activity-meta-button-text"));
	}

	// Method: Verify if radio button is selected
	public void clickInpatientRadioButton() {
		waitForElement(100, (By.cssSelector("[type='text']")));
		isRadioButtonSelected(By.className("v-input--selection-controls__ripple primary--text"));
	}
	
	public void selectInpatientRadioButton() throws InterruptedException {
//		waitForElement(100, (By.cssSelector("#app [role='dialog']:nth-of-type(4) [type='text']")));
//		clickElement(By.xpath("//*[contains(text(),'" + "INPATIENT" + "')]"));
		clickElement(By.cssSelector("#app [role='dialog']:nth-of-type(4) [type='text']"));
		enterText((By.cssSelector("#app [role='dialog']:nth-of-type(4) [type='text']")), "Test");
	}
	
	public void clickSetButtonOnLocationDialogBox() {
		waitforClick(100, By.className("v-btn__content"));
		clickElement(By.className("v-btn__content"));
	}
	
	public void clickLocationDialog() {
		clickElement(By.cssSelector("v-text-field__slot"));
	}
}
