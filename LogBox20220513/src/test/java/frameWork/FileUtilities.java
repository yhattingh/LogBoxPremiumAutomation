package frameWork;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.testng.Assert;
public class FileUtilities extends BasePageFrameWork {
	
	
	private static XSSFWorkbook xssfWorkbook;
	private static XSSFSheet xssfSheet;
	private static XSSFRow xssfRow;
	private static XSSFCell xssfcell;
	// Method: To read data from Excel
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] data = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
			DataFormatter dataFormatter = new DataFormatter();
			XSSFRow xssfRow = xssfSheet.getRow(0);
			int noOfRows = xssfSheet.getPhysicalNumberOfRows();
			int noOfCols = xssfRow.getLastCellNum();
			data = new String[noOfRows - 1][noOfCols];
			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					data[i - 1][j] = dataFormatter.formatCellValue(xssfSheet.getRow(i).getCell(j));
					System.out.println((data[i - 1][j]));
					xssfWorkbook.close();
				}
			}
		}
		catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}
	
	public void setExcelFile(String excelFilePath, String sheetName) throws IOException {
		// Create an object of File class to open xls file
		File file = new File(excelFilePath);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		// creating workbook instance that refers to .xls file
		xssfWorkbook = new XSSFWorkbook(inputStream);
		// creating a Sheet object
		xssfSheet = xssfWorkbook.getSheet(sheetName);
	}
	public String getCellData(int rowNumber, int cellNumber) {
		// getting the cell value from rowNumber and cell Number
		xssfcell = xssfSheet.getRow(rowNumber).getCell(cellNumber);
		// returning the cell value as string
		return xssfcell.getStringCellValue();
	}
	//Method:  Get RowCount in Sheet
	public int getRowCountInSheet() {
		int rowcount = xssfSheet.getLastRowNum() - xssfSheet.getFirstRowNum();
		return rowcount;
	}
	//Method:  Set the Cell Value
	public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) throws IOException {
		// creating a new cell in row and setting value to it
		xssfSheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);
		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		xssfWorkbook.write(outputStream);
	}
		
	// Method: Read from a PDF Document
	public String readPDFContent(String appUrl, int expectedNoPages) throws Exception {
		URL url = new URL(appUrl);
		InputStream inputStream = url.openStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream); // BufferedInputStream refers to
																						// the file to parse
		PDDocument pdDocument = null;
		String output = null;
		try {
			pdDocument = PDDocument.load(bufferedInputStream);
			output = new PDFTextStripper().getText(pdDocument);
			int numberOfPages = getPageCount(pdDocument);
			Assert.assertEquals(numberOfPages, expectedNoPages);
		} finally {
			if (pdDocument != null) {
				pdDocument.close();
			}
			bufferedInputStream.close();
			inputStream.close();
		}
		return output;
	}
	public int getPageCount(PDDocument document) {
		int pageCount = document.getNumberOfPages();
		return pageCount;
	}
	// Method: Reset an output file in case it is corrupted or still open - A new conflict created here
	public void resetOutPutFile(String OutputFileName) {
		try {
			FileWriter fileWriter = new FileWriter(OutputFileName, false);
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
	String outputDirectory = getDataConfigProperties("outputDir");
	String outputFile = outputDirectory + "OutputFile.txt";
	// Method: Write to file
	//Creating a new conflict here
	public void writingToFile(String column1, String column2) {
		boolean bool = true;
		String result;
		if (bool) {
			result = "This is the correct result";
		} else {
			result = "This is the incorrect result";
		}
		String content = column1 + "," + column2 + "," + result;
		try {
			Files.write(Paths.get(outputFile), (content + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("The exception error is: " + e.getMessage());
		}
	}
}