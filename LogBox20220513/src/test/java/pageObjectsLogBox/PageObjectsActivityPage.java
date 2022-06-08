package pageObjectsLogBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import frameWork.BasePageFrameWork;

public class PageObjectsActivityPage extends BasePageFrameWork {

	public void clickMoreButton() {
		clickElement(By.cssSelector(".btn-table-row .material-icons"));
	}

	public void selectOptionFromMoreButtonList(String optiontext) {
		clickElement(By.linkText(optiontext));
	}

	public void inputCaseFileProblem(String presentingproblem) throws InterruptedException {
		enterText(By.cssSelector("input[name='Presenting Problem']"), presentingproblem);
	}

	public void clickSaveButtonCaseFile() {
		clickElement(
				By.cssSelector("div:nth-of-type(11) .primary.theme--light.v-btn.v-btn--depressed > .v-btn__content"));
	}

	public void clickCaseFilesDropdown() throws InterruptedException{
		clickElement(By.cssSelector(".justify-space-between.row"));
	}

	public String getTextFromFirstItemOnCaseFilesList() {
		String textFromCaseFilesList = getElementText(
				By.cssSelector("#app .selectCaseFileListItem:nth-of-type(1) .v-list__tile__title"));
		return textFromCaseFilesList;
	}

//	WebElement select = driver.findElement(By.id("gender"));
//	List<WebElement> options = select.findElements(By.tagName("Male")); 
//	for (WebElement option : options) ...


	public static void anotherList(String[] args) {
		//List<WebElement> l = driver.findElements(By.className("v-select-list v-card theme--light"));
		//List<WebElement> l = driver.findElements(By.className("v-menu__content theme--light menuable__content__active"));
		List<WebElement> l = driver.findElements(By.className("selectCaseFileListItem"));
		System.out.println("Elements are: ");
		System.out.println("size = :" + l.size());
		System.out.println("listIterator === " + l);
	      for(int j = 0; j< l.size(); j++) {
	         //identify separate div
	         String v = l.get(j).getText();
	         System.out.println("Elements are: ");
	         System.out.println(v);
	      }
	      }

	  	public static void main1(String[] args) throws InterruptedException {			
				
	        //Click on Dropdown				
					
	  				
	        driver.findElement((By.className(".v-select__selections")));				
	         				
	        List<WebElement> allOptions = driver.findElements(By.cssSelector("div[role='listbox']"));				
	        System.out.println(allOptions.size());				
	        				
	             				
	        for(int i = 0; i<=allOptions.size()-1; i++) {				
	        	System.out.println(allOptions.get(i).getText());			
	             				
	             				
	            if(allOptions.get(i).getText().equals("Case file added on 04-06-2022 09:00:45")) {				
	            	//if(allOptions.get(i).getText().contains("Initial case file")) {			
	                allOptions.get(i).click();				
	                break;				
	                 				
	            }				
	        }				
		}}	
	
	
////Create instance of Javascript executor				
//JavascriptExecutor je = (JavascriptExecutor) driver;				
////Identify the WebElement which will appear after scrolling down				
//WebElement element = driver.findElement(By.tagName("...."));				
////now execute query which actually will scroll until that element is not appeared on page.				
//je.executeScript("arguments[0].scrollIntoView(true);",element);
