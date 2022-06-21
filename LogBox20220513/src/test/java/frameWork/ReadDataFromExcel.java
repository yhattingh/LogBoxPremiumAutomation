package frameWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameWork.FileUtilities;

public class ReadDataFromExcel extends BasePageFrameWork {
	FileUtilities fileUtilities = new FileUtilities();

	@DataProvider(name = "logBoxUserAccount")
	public Object[][] getDataFromExcel(String filename, String sheetname) throws IOException {
		String inputDirectory = getDataConfigProperties("inputDir");
		Object[][] errObj = fileUtilities.getExcelData(inputDirectory + filename, sheetname);
		return errObj;

	}
	
	@DataProvider(name = "MDTMeetings")
	public Object[][] getDataFromExcelTakeALot() {
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
