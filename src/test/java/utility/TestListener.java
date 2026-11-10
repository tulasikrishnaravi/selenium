package utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.usermanage.data.selenium.BaseTest;

public class TestListener implements ITestListener{

	private static final Logger logger= LogManager.getLogger(TestListener.class);
	
	
	// ExtentReports variables
    private static ExtentReports extent;
    private ExtentTest test;
	
    @Override
    public void onStart(ITestContext context) {
        // Triggered once before the suite starts to create the HTML file
        extent = ExtentManager.createInstance();
    }
    
    
    
    @Override
	public void onTestStart(ITestResult result) {
		
		logger.info("Starting test"+" "+result.getName());
		test = extent.createTest(result.getName());
		
	}
	
   @Override 
   public void onTestSuccess(ITestResult result) {
		
		logger.info("passed test"+" "+result.getName());
		test.log(Status.PASS, "Test Passed Successfully");
	}
	
   @Override 
   public void onTestFailure(ITestResult result) {
		
	   logger.error("!!! FAILED TEST: " + result.getName() + " !!!");
       logger.error("Failure Exception: ", result.getThrowable());
       test.fail(result.getThrowable());
       // 1. Fetch the active test class instance running in memory
       Object currentClass = result.getInstance();

       // 2. Cast the instance to BaseTest to safely access its WebDriver handle
       WebDriver driver = ((BaseTest) currentClass).getDriver();

       // 3. Trigger screenshot capture if driver is active
       if (driver != null) {
    	// Capture screenshot and get the path
           String screenshotPath =Utility.captureScreenshot(driver,result.getName());
           if (screenshotPath != null) {
               // Attach the image directly to the HTML report
               test.addScreenCaptureFromPath(screenshotPath);
           }
       } else {
           logger.warn("Unable to capture screenshot: WebDriver instance was null.");
       }
    
   }
   
   @Override
   public void onFinish(ITestContext context) {
       // Write everything to the HTML file on disk
       if (extent != null) {
           extent.flush();
       }
   }
	
}
