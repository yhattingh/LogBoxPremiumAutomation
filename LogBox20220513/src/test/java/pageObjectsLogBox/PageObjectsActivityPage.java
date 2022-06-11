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

//	WebElement select = driver.findElement(By.id("gender"));
//	List<WebElement> options = select.findElements(By.tagName("Male")); 
//	for (WebElement option : options) ...

	public static void anotherList(String[] args) {
		// List<WebElement> l = driver.findElements(By.className("v-select-list v-card
		// theme--light"));
		// List<WebElement> l = driver.findElements(By.className("v-menu__content
		// theme--light menuable__content__active"));
		List<WebElement> l = driver.findElements(By.className("selectCaseFileListItem"));
		System.out.println("Elements are: ");
		System.out.println("size = :" + l.size());
		System.out.println("listIterator === " + l);
		for (int j = 0; j < l.size(); j++) {
			// identify separate div
			String v = l.get(j).getText();
			System.out.println("Elements are: ");
			System.out.println(v);
		}
	}

	
		public void selectItemFromDropdownList(String caseFileName) {

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
		
		public void selectItemFromDropdownList1(String caseFileName) {
			
		JavascriptExecutor je = (JavascriptExecutor) driver;

		WebElement element = driver.findElement(By.className("v-select__selections"));
		System.out.println("number of returned rows: " + " " + element.getSize());
		
		je.executeScript("javascript:window.scrollBy(0,250)");
//		je.executeScript("arguments[0].scrollIntoViewIfNeeded(true);",element);
//		for (int i = 0; i <= element.size() - 1; i++) {
			System.out.println("show element text" + " " + element.getText());
		if (element.getText().contains(caseFileName)) {
			element.click();
		}
		
		System.out.println("What is the element?" + " " + element.getText());
		
		//https://stackoverflow.com/questions/3401343/scroll-element-into-view-with-selenium
		//https://www.tutorialspoint.com/scroll-element-into-view-with-selenium
			
			}
	}


////Create instance of Javascript executor				
//JavascriptExecutor je = (JavascriptExecutor) driver;				
////Identify the WebElement which will appear after scrolling down				
//WebElement element = driver.findElement(By.tagName("...."));				
////now execute query which actually will scroll until that element is not appeared on page.				
//je.executeScript("arguments[0].scrollIntoView(true);",element);
