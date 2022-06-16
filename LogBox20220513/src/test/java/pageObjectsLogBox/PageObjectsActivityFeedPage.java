package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityFeedPage extends BasePageFrameWork{
	
	// Method:  Click Search Activities
	public void searchForActivity(String searchedactivity) throws InterruptedException {
		waitForElement(200, By.cssSelector("input"));
		enterText(By.cssSelector("input"), searchedactivity);
	}
	
	// Method:  Click Activity on Activity Feed Page
	public void clickActivity() {
	waitUntilElementNoLongerDisplays(50, By.className(".pageLoadingBar"));
	clickElement(By.cssSelector("div:nth-of-type(1) > .mb-3.pb-2.theme--light.v-card.v-sheet"));
}
	
	// Method:  Click on Activity to Edit
	public void clickToEditActivity() {
		waitforClick(100, (By.cssSelector("[class] [class='col col-12']:nth-of-type(3) [class] .small-flex-child:nth-of-type(1)")));
		clickElement(By.cssSelector("[class] [class='col col-12']:nth-of-type(3) [class] .small-flex-child:nth-of-type(1)"));
	}
	

	
	// Method:  Click DateTime
	public void clickDateTimeButton() {
		clickElement(By.cssSelector("div:nth-of-type(1) > .activity-meta-button.theme--light.v-btn.v-btn--block.v-btn--has-bg.v-size--small  .activity-meta-button-text"));
	}
	
	// Method:  Click on Date in Date Dialog Box
	public void clickInDateField() {
		clickElement(By.cssSelector("[data-cy='date-picker']"));
	}
	
	// Method:  Select a Different Date
	public void selectDifferentDate() {
		
		
	}
}
