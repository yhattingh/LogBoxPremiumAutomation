package frameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

public class Utilities extends BasePageFrameWork {

	// Method:  Create Screenshots
	public void takeSnapShot(String fileWithPath) throws IOException {

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File scrFile = scrShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(".\\target\\" + "surefire-reports-" + getAppConfigProperties("build.timestamp")
				+ "//images//" + fileWithPath);

		FileUtils.copyFile(scrFile, destFile);

		Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
				+ "'height='200' width='200'/> </a>");
	}

	// Method:  Write to output file

	// Method: to get the property values from the app.properties file for screenshots
	public String getAppConfigProperties(String propertyName) { 
		
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(".\\target\\app.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(propertyName);
	}

	// Method: Return the time now for screenshots
	public String timeReturn() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
		return dtf.format(now);
	}
}