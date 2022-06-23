package listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import frameWork.BasePageFrameWork;
import frameWork.Utilities;

public class Listeners extends BasePageFrameWork implements ITestListener {

	Utilities utilities = new Utilities();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		super.cleanUp();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public void clearText(By pLocator) {
		// TODO Auto-generated method stub
		super.clearText(pLocator);
	}

	@Override
	public void clickElement(By pLocator) {
		// TODO Auto-generated method stub
		super.clickElement(pLocator);
	}

	@Override
	public void closeChildBrowserTab() {
		// TODO Auto-generated method stub
		super.closeChildBrowserTab();
	}

	@Override
	public void closeParentBrowserTab() {
		// TODO Auto-generated method stub
		super.closeParentBrowserTab();
	}

	@Override
	public boolean elementExists(By pLocator) {
		// TODO Auto-generated method stub
		return super.elementExists(pLocator);
	}

	@Override
	public void enterText(By pLocator, String enterText) throws InterruptedException {
		// TODO Auto-generated method stub
		super.enterText(pLocator, enterText);
	}

	@Override
	public String getDataConfigProperties(String propertyName) {
		// TODO Auto-generated method stub
		return super.getDataConfigProperties(propertyName);
	}

	@Override
	public WebElement getElement(By pLocator) {
		// TODO Auto-generated method stub
		return super.getElement(pLocator);
	}

	@Override
	public String getElementText(By pLocator) {
		// TODO Auto-generated method stub
		return super.getElementText(pLocator);
	}

	@Override
	public String getInnerHTML(By pLocator) {
		// TODO Auto-generated method stub
		return super.getInnerHTML(pLocator);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}

	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return super.getURL();
	}

	@Override
	public void selectDropdown(By pLocator, String pValue) {
		// TODO Auto-generated method stub
		super.selectDropdown(pLocator, pValue);
	}

	@Override
	public void switchToNewTab() {
		// TODO Auto-generated method stub
		super.switchToNewTab();
	}

	@Override
	public void switchToParent() {
		// TODO Auto-generated method stub
		super.switchToParent();
	}

	@Override
	public void waitforClick(int elementWait, By pLocator) {
		// TODO Auto-generated method stub
		super.waitforClick(elementWait, pLocator);
	}

	@Override
	public void waitForElement(int elementWait, By pLocator) {
		// TODO Auto-generated method stub
		super.waitForElement(elementWait, pLocator);
	}

	@Override
	public void waitForUrl(int elementWait, String pLocator) {
		// TODO Auto-generated method stub
		super.waitForUrl(elementWait, pLocator);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		try {
			utilities.takeSnapShot("On test success" + utilities.timeReturn() + ".png");
			System.out.println("Listener:Test Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			utilities.takeSnapShot("On test failure" + utilities.timeReturn() + ".png");
			System.out.println("Listener:  On test failure");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		try {
			utilities.takeSnapShot("On test skipped" + utilities.timeReturn() + ".png");
			System.out.println("Listener:  On test failure");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
