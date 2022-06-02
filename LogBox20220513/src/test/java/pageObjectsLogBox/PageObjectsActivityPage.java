package pageObjectsLogBox;

import org.openqa.selenium.By;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityPage extends BasePageFrameWork{
	
//	public void clickMoreButton() {
//		driver.findElement(By.cssSelector(".btn-table-row .material-icons")).click();
//	}
//	
//	public void selectOptionFromMoreButtonList(String optiontext) {
//		driver.findElement(By.linkText(optiontext)).click();
//	}
//	
//	public void inputCaseFileProblem(String presentingproblem) throws InterruptedException {
//		driver.findElement(By.cssSelector("input[name='Presenting Problem']")).sendKeys(presentingproblem);
//		//wait(100);
//	}
//	
//	public void clickSaveButtonCaseFile() {
//		//driver.findElement(By.cssSelector("div:nth-of-type(13) .primary.theme--light.v-btn.v-btn--depressed > .v-btn__content")).click();
//		driver.findElement(By.cssSelector("div:nth-of-type(13) .primary.theme--light.v-btn.v-btn--depressed")).click();
//	}
	
	public void clickMoreButton() {
		clickElement(By.cssSelector(".btn-table-row .material-icons"));
	}
	
	public void selectOptionFromMoreButtonList(String optiontext) {
		clickElement(By.linkText(optiontext));
	}
	
	public void inputinputCaseFileProblem(String presentingproblem) {
		enterText(By.cssSelector("input[name='Presenting Problem']"), presentingproblem);
	}
	
	public void clickSaveButtonCaseFile() {
		clickElement(By.cssSelector("div:nth-of-type(13) .primary.theme--light.v-btn.v-btn--depressed"));
	}
}

