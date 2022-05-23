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

}
