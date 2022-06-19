package testsLogBox.MDTMeetings;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsMDTPage;

public class MDTMeetingsTests extends BasePageFrameWork{
	
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	BasePageFrameWork basePageFrameWork = new BasePageFrameWork();
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsMDTPage pageObjectsMDTPage = new PageObjectsMDTPage();
	
	@AfterTest
	public void  cleanUp() {
		basePageFrameWork.cleanUp();
	}
	
	// User story five
	@Test
	public void shouldNotBeAllowedToSaveAMDTMeetingWithoutMeetingCoordinator() throws InterruptedException, IOException {
		
		String hospitalName = "Wits Donald Gordon Medical Centre";
		int dateAdjustment = 2;
		int timeAdjustment = 30;
		String meetingDate; 
		String meetingTime;
		String meetingNotes = "Notes for Dr Terreblanche regarding appointment";
		String doctorName = "Dr Terreblanche";
		String docphonenumber = "021-555-8888";
		String recurringDateType = "Weekly";
		String expectedError = "Failed to save Meeting: Please fill in the form correctly";
	
		pageObjectsBrochurePage.selectPracticeAndClickLoginButton();
		pageObjectsBrochurePage.insertActivityUsernameAndPasswordFromExcel();
		pageObjectsBrochurePage.clickLoginButtonToSubmitUsernameAndPassword();
		basePageLogBox.clickMDTMeetings();
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
		pageObjectsMDTPage.enterMeetingNotes(meetingNotes);
		pageObjectsMDTPage.enterDoctorName(doctorName);
		pageObjectsMDTPage.enterDoctorPhoneNumber(docphonenumber);
		pageObjectsMDTPage.clickSaveButtonOnMDTMeetingList();
		System.out.println("Error message displayed : " + " " + pageObjectsMDTPage.getErrorMsgOnSave());
		Assert.assertTrue(pageObjectsMDTPage.getErrorMsgOnSave().equals(expectedError));
		Reporter.log("Validation passed:  User is not allowed to save a MDT meeting without a Coordinator");
	}
}
