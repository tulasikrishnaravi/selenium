package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_RETRY_COUNT = 2; // Test will run a total of 3 times (1 initial + 2 retries)

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) { 
            if (count < MAX_RETRY_COUNT) {
                count++;
                System.out.println("Retrying test " + result.getName() + " for the " + count + " time.");
                return true; // Returning true tells TestNG to re-run the test
            }
        }
        return false; // Stop retrying once the limit is reached
    }
}