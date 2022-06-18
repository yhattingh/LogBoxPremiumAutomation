package pageObjectsLogBox;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import frameWork.BasePageFrameWork;

public class PageObjectsMDTPage extends BasePageFrameWork {

	// Method: Click the ADD NEW MDT MEETING Button
	public void clickAddNewMDTMeetingButton() {
		clickElement(By.cssSelector(".v-btn--is-elevated"));
	}

	// Method: Click the Repeat Meeting Schedule Checkbox
	public void selectRepeatMeetingScheduleCheckBox() {
		clickElement(By.cssSelector(".v-input--selection-controls__ripple"));
	}

	// Method: Select a Recurring Date Type Option
	public void selectRecurringDateType(String repeatoptions) {

		driver.findElement(By.cssSelector(".v-select__selections"));

		clickElement(By.cssSelector(".v-select__selections"));

		List<WebElement> allOptions = driver.findElements(By.className("v-list-item__title"));
		System.out.println("number of returned rows: " + " " + allOptions.size());

		for (int i = 0; i <= allOptions.size() - 1; i++) {
			System.out.println(allOptions.get(i).getText());

			if (allOptions.get(i).getText().contains(repeatoptions)) {
				allOptions.get(i).click();
				break;
			}
		}
	}

	// Method: Click the DONE button on the Repeat Dialog
	public void clickDoneButtonOnRepeatDialog() {
		clickElement(By.xpath("//*[contains(text(),'Done')]"));
	}

	// Method: Enter Meeting Notes
	public void enterMeetingNotes(String meetingnotes) throws InterruptedException {
		enterText(By.cssSelector("[class='col col-12']:nth-of-type(4) [type]"), meetingnotes);
	}

	// Method: Enter Doctor's Name
	public void enterDoctorName(String doctorname) throws InterruptedException {
		enterText(By.cssSelector("[name='doctor-0'] [type='text']"), doctorname);
	}

	// Method: Enter Doctor's Phone Number
	public void enterDoctorPhoneNumber(String docphonenumber) throws InterruptedException {
		enterText(By.cssSelector("input[name='contactNumber-0']"), docphonenumber);
	}

	// Method: Click Save Button on Main MDT Meeting Page
	public void clickSaveButtonOnMDTMeetingList() {
		clickElement(By.xpath("//*[contains(text(),'SAVE')]"));
	}

	// Method: Get the Error Displayed when a Required Field is Empty
	public String getErrorMsgOnSave() {
		waitForElement(100, (By.cssSelector(".error .v-snack__content")));
		String errorMsgOnSave = getElementText(By.cssSelector(".error .v-snack__content"));
		return errorMsgOnSave;
	}
}
