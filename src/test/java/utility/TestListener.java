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

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.createInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test " + getTestName(result));
        // CHANGE 1: unique name, so the 3 data-provider rows show separately in the report
        test = extent.createTest(getTestName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Passed test " + getTestName(result));
        test.log(Status.PASS, "Test Passed Successfully");
        // CHANGE 2: screenshot on pass
        attachScreenshot(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("!!! FAILED TEST: " + getTestName(result) + " !!!");
        logger.error("Failure Exception: ", result.getThrowable());
        test.fail(result.getThrowable());
        attachScreenshot(result, "FAIL");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    // CHANGE 3: one shared helper for pass and fail
    private void attachScreenshot(ITestResult result, String status) {
        Object instance = result.getInstance();
        if (!(instance instanceof BaseTest)) {
            return;
        }
        WebDriver driver = ((BaseTest) instance).getDriver();
        if (driver == null) {
            logger.warn("Unable to capture screenshot: WebDriver instance was null.");
            return;
        }
        String shotName = getTestName(result).replaceAll("[^a-zA-Z0-9_]", "") + "_" + status;
        String screenshotPath = Utility.captureScreenshot(driver, shotName);
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    // Builds "loginTest_standard_user_Success" from the data-provider row
    private String getTestName(ITestResult result) {
        Object[] params = result.getParameters();
        if (params != null && params.length >= 2) {
            // first column = username, last column = expected status
            // (the password column is skipped so it never ends up in file names or reports)
            return result.getName() + "_" + params[0] + "_" + params[params.length - 1];
        }
        return result.getName();
    }
}