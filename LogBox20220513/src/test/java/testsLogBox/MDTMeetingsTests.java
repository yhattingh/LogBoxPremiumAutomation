package testsLogBox;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import frameWork.WriteDataToFile;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsHomePage;
import pageObjectsLogBox.PageObjectsMDTPage;
import frameWork.ReadDataFromExcel;

public class MDTMeetingsTests extends BasePageFrameWork {

	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	BasePageFrameWork basePageFrameWork = new BasePageFrameWork();
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsMDTPage pageObjectsMDTPage = new PageObjectsMDTPage();
	WriteDataToFile writeDataToFile = new WriteDataToFile();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	PageObjectsHomePage pageObjectsHomePage = new PageObjectsHomePage();

	@AfterTest
	public void cleanUpAfterTest() {
		cleanUp();
	}
	
	@AfterMethod
	public void logOut() {
		basePageLogBox.logOutOfLogBox();
	}


	@Test(dataProvider = "MDTMeetings", dataProviderClass = ReadDataFromExcel.class)
	public void shouldNotBeAllowedToSaveAMDTMeetingWithoutMeetingCoordinator(String doctorName, String doctorPhone,
			String meetingNotes) throws InterruptedException, IOException {

		System.out.println("Excel data" + " " + "Doctor: " + doctorName + " " + "Phone:" + " " + doctorPhone + "Notes: "
				+ " " + meetingNotes);

		String hospitalName = "Wits Donald Gordon Medical Centre";
		int dateAdjustment = 2;
		int timeAdjustment = 30;
		String meetingDate;
		String meetingTime;
		String meetingNotesInput = meetingNotes;
		String doctorNameInput = doctorName;
		String docPhoneNumberInput = doctorPhone;
		String recurringDateType = "Weekly";
		String expectedError = "Failed to save Meeting: Please fill in the form correctly";

		writeDataToFile.writingToFile(doctorName, meetingNotes);

		// GIVEN
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();

		// WHEN
		pageObjectsHomePage.clickMDTMeetings();
		pageObjectsMDTPage.clickAddNewMDTMeetingButton();
		basePageLogBox.enterHospitalName(hospitalName);
		basePageLogBox.selectHospitalName(hospitalName);
		meetingDate = basePageFrameWork.getDateWithAddSubstractDays(dateAdjustment);
		System.out.println("The meeting date is: " + " " + meetingDate);
		basePageLogBox.enterDate(meetingDate);
		meetingTime = basePageFrameWork.getTimeWithAddSubstractMinutesUsedByStartTime(timeAdjustment);
		System.out.println("The meeting time is: " + " " + meetingTime);
		basePageLogBox.enterTime(meetingTime);
		pageObjectsMDTPage.selectRepeatMeetingScheduleCheckBox();
		pageObjectsMDTPage.selectRecurringDateType(recurringDateType);
		pageObjectsMDTPage.clickDoneButtonOnRepeatDialog();
		pageObjectsMDTPage.enterMeetingNotes(meetingNotesInput);
		pageObjectsMDTPage.enterDoctorName(doctorNameInput);
		pageObjectsMDTPage.enterDoctorPhoneNumber(docPhoneNumberInput);
		pageObjectsMDTPage.clickSaveButtonOnMDTMeetingList();
		System.out.println("Error message displayed : " + " " + pageObjectsMDTPage.getErrorMsgOnSave());

		// THEN
		Assert.assertTrue(pageObjectsMDTPage.getErrorMsgOnSave().equals(expectedError));
		Reporter.log("Validation passed:  User is not allowed to save a MDT meeting without a Coordinator");
	}
}
