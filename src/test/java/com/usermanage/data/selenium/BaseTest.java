package com.usermanage.data.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    // one WebDriver slot per thread; static final so there is a single holder shared by all tests
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Initializing ChromeDriver...");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        driver.set(webDriver);                 // store this thread's driver
    }

    // used by tests, page objects and TestListener
    public WebDriver getDriver() {
        return driver.get();                   // returns the current thread's driver
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            logger.info("Closing browser session...");
            webDriver.quit();
        }
        driver.remove();                       // clear the slot so the thread doesn't keep a dead driver
    }
}