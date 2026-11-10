package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static ExtentReports createInstance() {
        String path = System.getProperty("user.dir") + "/reports/AutomationReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Test Automation Report");
        reporter.config().setReportName("Selenium Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Environment", "QA");
        
        return extent;
    }
}