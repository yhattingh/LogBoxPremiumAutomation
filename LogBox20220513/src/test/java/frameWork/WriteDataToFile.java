package frameWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.testng.annotations.BeforeTest;

public class WriteDataToFile {

	// Instantiate the utilities class object
	FileUtilities fileUtilities = new FileUtilities();
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();

	String outputDirectory = fileUtilities.getDataConfigProperties("outputDir");
	String outputFile = outputDirectory + "output.txt";

	@BeforeTest
	public void setup() {
		fileUtilities.resetOutPutFile(outputFile);
	}

	// Method:  To read from the Excel file in the ReadDataFromExcel class and writes to output.txt file
	public void writingToFile(String string1, String string2) {

		boolean bool = true;

		String result;
		if (bool) {
			result = " This is the correct result";
		} else {
			result = " This is the incorrect result";
		}

		String content = result + ":" + " " + string1 + " " + "," + " " + string2;

		try {
			Files.write(Paths.get(outputFile), (content + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("the error is " + e.getMessage());
		}
	}
}
