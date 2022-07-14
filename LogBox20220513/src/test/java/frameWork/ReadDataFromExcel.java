package frameWork;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class ReadDataFromExcel extends BasePageFrameWork {
	FileUtilities fileUtilities = new FileUtilities();

	//Logbox Dataprovider Methods
	@DataProvider(name = "logBoxUserAccount")
	public Object[][] getDataFromExcel(String filename, String sheetname) throws IOException {
		String inputDirectory = getDataConfigProperties("inputDir");
		Object[][] errObj = fileUtilities.getExcelData(inputDirectory + filename, sheetname);
		return errObj;
	}

	@DataProvider(name = "MDTMeetings")
	public Object[][] getDataFromExcelMDTMeetings() {
		String excelDirectory = fileUtilities.getDataConfigProperties("inputDir");
		Object[][] errObj = fileUtilities.getExcelData(excelDirectory + "MDTMeetings.xlsx", "Sheet1");
		return errObj;
	}

	@DataProvider(name = "PreAdmissionPatients")
	public Object[][] getDataFromExcelPreAdmissionPatient() throws IOException {
		String inputDirectory = getDataConfigProperties("inputDir");
		Object[][] errObj = fileUtilities.getExcelData(inputDirectory + "PreAdmissionPatients.xlsx", "Sheet1");
		return errObj;
	}
}
