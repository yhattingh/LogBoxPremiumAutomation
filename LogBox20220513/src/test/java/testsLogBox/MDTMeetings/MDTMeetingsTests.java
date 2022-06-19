package testsLogBox.MDTMeetings;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameWork.BasePageFrameWork;
import frameWork.FileUtilities;
import frameWork.WriteDataToFile;
import pageObjectsLogBox.BasePageLogBox;
import pageObjectsLogBox.PageObjectsBrochurePage;
import pageObjectsLogBox.PageObjectsMDTPage;

public class MDTMeetingsTests extends BasePageFrameWork{
	
	PageObjectsBrochurePage pageObjectsBrochurePage = new PageObjectsBrochurePage();
	BasePageFrameWork basePageFrameWork = new BasePageFrameWork();
	BasePageLogBox basePageLogBox = new BasePageLogBox();
	PageObjectsMDTPage pageObjectsMDTPage = new PageObjectsMDTPage();
	WriteDataToFile writeDataToFile = new WriteDataToFile();
	
	@AfterTest
	public void  cleanUp() {
		basePageFrameWork.cleanUp();
	}
	
	// User story five
	@Test (dataProvider = "MDTMeetings")
	public void shouldNotBeAllowedToSaveAMDTMeetingWithoutMeetingCoordinator(String doctorName, String doctorPhone, String meetingNotes) throws InterruptedException, IOException {
		System.out.println("What am I reading from excel?" + " " + doctorName + " " + doctorPhone + " " + meetingNotes);
//	public void shouldNotBeAllowedToSaveAMDTMeetingWithoutMeetingCoordinator() throws InterruptedException, IOException {
		
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
		
//		writeDataToFile.writingToFile(doctorName);
	
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
		pageObjectsMDTPage.enterMeetingNotes(meetingNotesInput);
		pageObjectsMDTPage.enterDoctorName(doctorNameInput);
		pageObjectsMDTPage.enterDoctorPhoneNumber(docPhoneNumberInput);
		pageObjectsMDTPage.clickSaveButtonOnMDTMeetingList();
		System.out.println("Error message displayed : " + " " + pageObjectsMDTPage.getErrorMsgOnSave());
		Assert.assertTrue(pageObjectsMDTPage.getErrorMsgOnSave().equals(expectedError));
		Reporter.log("Validation passed:  User is not allowed to save a MDT meeting without a Coordinator");
	}
	
	FileUtilities fileUtilities = new FileUtilities();
	
	@Test (dataProvider = "MDTMeetings")
	public void searchItem(String patientName) {
		System.out.println("Patient name read form Excel sheet: " + " " + patientName);
	}
	
	@DataProvider(name = "MDTMeetings")
	public Object[][] getDataFromExcelTakeALot() {
		String excelDirectory = fileUtilities.getDataConfigProperties("inputDir");
		Object[][] errObj = fileUtilities.getExcelData(excelDirectory + "MDTMeetings.xlsx", "Sheet1");
		return errObj;
	}
}
