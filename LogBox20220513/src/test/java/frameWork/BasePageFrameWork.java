package frameWork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePageFrameWork {
	// Declare the Webdriver
	public static WebDriver driver;

	// Set browser URL
	public BasePageFrameWork() {
		if (driver == null) {

			String browser = getDataConfigProperties("browser");
			String URL = getDataConfigProperties("URL");

			// Method: Check if parameter passed as "Chrome"
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		}
	}
	
	// Method: Cleanup
	public void cleanUp() {
		driver.quit();
	}

	// Method: Clear text from Field
	public void clearText(By pLocator) {
		waitforClick(50, pLocator);
		driver.findElement(pLocator).clear();
	}

	// Method: Select all text in a field and delete
	public void deleteText(By pLocator) {
		waitforClick(50, pLocator);
		driver.findElement(pLocator).sendKeys(Keys.CONTROL + "a");
		driver.findElement(pLocator).sendKeys(Keys.DELETE);
	}

	// Method: Click on an Element
	public void clickElement(By pLocator) {
		waitforClick(100, pLocator);
		getElement(pLocator).click();
	}

	// Method: Close Browsers
	public void closeChildBrowserTab() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		String parentWindowID = iterator.next();
		String childWindowID = iterator.next();
		driver.switchTo().window(childWindowID);
		driver.close();
		driver.switchTo().window(parentWindowID);
	}

	public void closeParentBrowserTab() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		String parentWindowID = iterator.next();
		String childWindowID = iterator.next();
		driver.switchTo().window(parentWindowID).close();
	}

	// Method: Check that Element exists
	public boolean elementExists(By pLocator) {
		boolean display = getElement(pLocator).isDisplayed();
		return display;
	}

	// Method: Enter text in Field
	public void enterText(By pLocator, String enterText) throws InterruptedException {
		waitForElement(30, pLocator);
		driver.findElement(pLocator).sendKeys(enterText);
	}

	// Method: Read the Config File
	public String getDataConfigProperties(String propertyName) {
		// Properties set
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(propertyName);
	}

	// Method: Get Element
	public WebElement getElement(By pLocator) {
		waitforClick(30, pLocator);
		return driver.findElement(pLocator);
	}

	// Method: Get text on Element
	public String getElementText(By pLocator) {
		String elementText = getElement(pLocator).getText();
		return elementText;
	}

	// Method: Get text on Element using innerHTML
	public String getInnerHTML(By pLocator) {
		String elementText = getElement(pLocator).getAttribute("innerHTML");
		System.out.println(elementText);
		return elementText;
	}

	// Method: Get Title
	public String getTitle() {
		String getTitle = driver.getTitle();
		return getTitle;
	}

	// Method: Get current URL
	public String getURL() {
		String getCurrentURL = driver.getCurrentUrl();
		return getCurrentURL;
	}

	// Method: Select from Dropdown
	public void selectDropdown(By pLocator, String pValue) {
		waitForElement(100, pLocator);
		Select selectDropDown = new Select(getElement(pLocator));
		selectDropDown.selectByVisibleText(pValue);
	}

	// Method: Switch Window / Tab
	public void switchToNewTab() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowID = it.next();
		String childWindowID = it.next();
		driver.switchTo().window(childWindowID);
	}

	// Method: Switch to Parent Window / Tab
	public void switchToParent() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowID = it.next();
		String childWindowID = it.next();
		driver.switchTo().window(parentWindowID);
	}

	// Method: Wait for Click
	public void waitforClick(int elementWait, By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.elementToBeClickable(pLocator));
	}

	// Method: Wait for Element
	public void waitForElement(int elementWait, By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pLocator));
	}

	// Method: Wait for Element To No Longer Display
	public void waitUntilElementNoLongerDisplays(int elementWait, By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(pLocator));
	}

	// Method: Wait for URL
	public void waitForUrl(int elementWait, String pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.urlContains(pLocator));
	}

	// Method:To return the formatted current date and time
	public String getLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		return localDateTimeString;
	}

	// Method:To return the formatted current date only
	public String getLocalDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDate = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return localDate;
	}

	// Method: To return the formatted current time only
	public String getCurrentTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localTime = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		return localTime;
	}
	
	// Method:To return the formatted current date plus n days in word format"
		public String getDateInWordFormat(int numberofdays) {
			LocalDate localDate = LocalDate.now();
			LocalDate newDate = localDate.plusDays(numberofdays);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM");
			return dtf.format(newDate);
		}

	// Method: Add or Substract n Days from LocalDate
	public String getDateWithAddSubstractDays(int numberofdays) {
		LocalDate localDate = LocalDate.now();
		LocalDate newDate = localDate.plusDays(numberofdays);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return dtf.format(newDate);
	}

	// Method: Add or Substract n Months from LocalDate
	public String getDateWithAddSubstractMonths(int numberofmonths) {
		LocalDate localDate = LocalDate.now();
		LocalDate newDate = localDate.plusMonths(numberofmonths);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return dtf.format(newDate);
	}

	// Method: Add or Substract n Minutes from LocalDate
	public String getTimeWithAddSubstractMinutesUsedByStartTime(int numberofminutes) {
		LocalTime localTime = LocalTime.now();
		LocalTime newMinutes = localTime.plusMinutes(numberofminutes);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
		return dtf.format(newMinutes);
	}

	// Method: Add or Substract n Minutes from LocalDate
	public String getTimeWithAddSubstractMinutes(int numberofminutes) {
		LocalTime localTime = LocalTime.now();
		LocalTime newMinutes = localTime.plusMinutes(numberofminutes);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		return dtf.format(newMinutes);
	}

	// Method: Add or Substract n Minutes from LocalDate
	public String getTimeWithAddSubstractHours(int numberofhours) {
		LocalTime localTime = LocalTime.now();
		LocalTime newMinutes = localTime.plusHours(numberofhours);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		return dtf.format(newMinutes);
	}

	// Method: If Radio Button is not Selected, Click Radio Button
	public void isRadioButtonSelected(By pLocator) {
		WebElement radioButton = driver.findElement(pLocator);
		if (radioButton.isSelected()) {
		} else
			radioButton.click();
	}

	// Method: Get the Number of Rows in a Table
	public int getNumberOfTableRows(By pLocatorTable, By pLocatorRow) {
		WebElement webElement = driver.findElement(pLocatorTable);
		List<WebElement> rows = webElement.findElements(pLocatorRow);
		return rows.size();
	}

	// Method: Check if Table is Empty
	public boolean checkIfTableIsEmpty(By pLocator, By pLocatorRow) {
		WebElement webElement = driver.findElement(pLocator);
		List<WebElement> rows = webElement.findElements(pLocatorRow);
		return rows.isEmpty();
	}

	// Method: To Scroll Item into view in a List
	public void scrollIntoView(By pLocator) {
		WebElement element = driver.findElement(pLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
