package com.usermanage.data.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Initializing ChromeDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Expose driver instance to TestListener via reflection/casting
    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing browser session...");
            driver.quit();
        }
    }
}
