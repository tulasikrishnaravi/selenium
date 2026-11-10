package utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static String captureScreenshot(WebDriver driver,String testName) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File tempfile=ts.getScreenshotAs(OutputType.FILE);
		
		String destinationpath=System.getProperty("user.dir")+"/screenshots/" + testName + ".png";
		File destFile= new File(destinationpath);
		try {
			destFile.getParentFile().mkdirs();
			FileHandler.copy(tempfile, destFile);
			System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
			return destinationpath; // Return the path for ExtentReports
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to copy screenshot: " + e.getMessage());
			return null;
		}
		
				
	}
}
