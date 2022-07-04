package pageObjectsLogBox;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import frameWork.BasePageFrameWork;

public class PageObjectsHomePage extends BasePageFrameWork {

	// Method: Search for a Patient on the Practice Patient List
	public void searchPracticePatientsInSearchBar(String patientname) throws InterruptedException {
		enterText(By.cssSelector(".v-text-field__slot [type]"), patientname);
	}

	// Method: Click on the Searched Patient
	public void clickOnSearchedPatient(String patientFirstName) {
		try {
			this.driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
			this.waitUntilElementNoLongerDisplays(5, By.className(".pageLoadingBar"));
			clickElement(By.cssSelector("tbody .pointer:nth-of-type(1) .text-start"));

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// Method: Click on the Ellipse Next to Patient Name
	public void clickOnEllipseNextToPatientName() {
		try {
			this.driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
			clickElement(By.cssSelector("[class='text-right pa-0'] .material-icons"));

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//Method: Click drawer opener
	public void clickdrawerbutton() {
		clickElement(By.cssSelector(" .v-list-item__icon.pointer .material-icons"));
	}
	
	// Method: Click on Message Patient Button in Left Menu
		public void clickOnMessagePatientButtonInLeftMenu() {
			//waitForElement(100, (By.cssSelector("tr > th:nth-of-type(2)")));
			clickElement(By.cssSelector(" .v-list-item__icon.pointer .material-icons"));
			clickElement(By.cssSelector("[class] [tabindex='0']:nth-of-type(3) .material-icons"));
		}

	// Method: Click on Pre-Admissions Button in Left Menu
	public void clickOnPreAdmissionButtonInLeftMenu() {
		clickElement(By.cssSelector("[data-cy='lb-practice-nav-drawer-pre-admission'] .material-icons"));
	}

	// Method: To Click on the More Options next to Patient Name
	public void clickOnListOptionInEllipseMenu(String listoption) {
		driver.findElement(By.className("v-menu__content theme--light menuable__content__active"));

		List<WebElement> listOptions = driver
				.findElements(By.className("v-menu__content theme--light menuable__content__active"));
		System.out.println(listOptions.size());

		for (int i = 0; i <= listOptions.size() - 1; i++) {
			System.out.println(listOptions.get(i).getText());
			if (listOptions.get(i).getText().equals(listoption)) {
				listOptions.get(i).click();
				break;
			}
		}
	}

	// Method: Click on Refresh Button
	public void clickRefreshButton() {
		driver.navigate().refresh();
	}

	// Method: Click on MDT Meeting in Quick Links Bar
	public void clickMDTMeetings() {
		waitForElement(100, (By.xpath(
				"//div[@id='app']/div[@class='v-application--wrap']/div[1]/nav//a[@href='#/mdt-meeting']//i[.='groups']")));
		clickElement(By.xpath(
				"//div[@id='app']/div[@class='v-application--wrap']/div[1]/nav//a[@href='#/mdt-meeting']//i[.='groups']"));
	}
}
