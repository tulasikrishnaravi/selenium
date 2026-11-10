package com.usermanage.data.selenium;

import java.sql.Driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;

public class Hope extends BaseTest{

	
	
	@DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() {
        return new Object[][] {
            // Username, Password, Expected Status
            {"standard_user", "secret_sauce", "Success"},      // Row 1: Valid Login
            {"locked_out_user", "secret_sauce", "Locked"},     // Row 2: Locked Account
            {"standard_user", "wrong_password", "Invalid"}     // Row 3: Bad Password
        };
    }
	
	@Test(priority = 3, dataProvider = "loginCredentials")
	public void loginTest(String username, String password, String expectedStatus) {
	    logger.info("Testing login for user: " + username);
	    driver.get(ConfigReader.getProperty("url"));

	    LoginPage loginpage = new LoginPage(driver);
	    loginpage.setUsername(username);
	    loginpage.setPassword(password);
	    loginpage.clickLoginButton();

	    switch (expectedStatus) {
	        case "Success":
	            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
	                    "Valid user should reach the inventory page");
	            break;

	        case "Locked":
	            String lockedMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();
	            Assert.assertTrue(lockedMsg.contains("locked out"), "Unexpected message: " + lockedMsg);
	            break;

	        case "Invalid":
	            String invalidMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();
	            Assert.assertTrue(invalidMsg.contains("do not match"), "Unexpected message: " + invalidMsg);
	            break;
	    }
	}
	
	
@Test(priority=2)	
public void found() {                     // by using testng we have ran directly and triggered the browswer and url.
		logger.info("Naviagting to the page");
		driver.get(ConfigReader.getProperty("url"));   // to open the link now
		LoginPage loginpage=new LoginPage(driver);
		loginpage.setUsername("standard_user");
		loginpage.setPassword( "secret_sauce");
		loginpage.clickLoginButton();
        
		
		// 2. Capture the actual browser state
        logger.info("Verifying page title after login...");
        String actualTitle = driver.getTitle();
        
     // 3. Force a failure to trigger onTestFailure() in your listener
        // The actual title is likely "Swag Labs", so expecting "Forced Error Title" will fail the test.
        Assert.assertEquals(actualTitle, "Forced Error Title", "Intentional mismatch to test screenshot capture");
        //Assert.assertEquals(actualTitle, "Swag Labs");		
	}

@Test(priority=1)		
public void found1() {                     // by using testng we have ran directly and triggered the browswer and url.
		logger.info("Naviagting to the page");
		driver.get(ConfigReader.getProperty("url"));   // to open the link now
		LoginPage loginpage=new LoginPage(driver);
		loginpage.setUsername("standard_user");
		loginpage.setPassword( "secret_sauce");
		loginpage.clickLoginButton();
        
		
		// 2. Capture the actual browser state
        logger.info("Verifying page title after login...");
        String actualTitle = driver.getTitle();
        
     // 3. Force a failure to trigger onTestFailure() in your listener
        // The actual title is likely "Swag Labs", so expecting "Forced Error Title" will fail the test.
        //Assert.assertEquals(actualTitle, "Forced Error Title", "Intentional mismatch to test screenshot capture");
        Assert.assertEquals(actualTitle, "Swag Labs");		
	}
/*
public static void main(String args[]) {  // ran using main method , now i want to run using testng
	Hope hope=new Hope();
	hope.found();
}
*/
}
