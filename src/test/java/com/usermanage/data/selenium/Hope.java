package com.usermanage.data.selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;

public class Hope extends BaseTest {

    @DataProvider(name = "loginCredentials", parallel = true)
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
        getDriver().get(ConfigReader.getProperty("url"));

        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.setUsername(username);
        loginpage.setPassword(password);
        loginpage.clickLoginButton();

        switch (expectedStatus) {
            case "Success":
                Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"),
                        "Valid user should reach the inventory page");
                break;

            case "Locked":
                String lockedMsg = getDriver().findElement(By.cssSelector("[data-test='error']")).getText();
                Assert.assertTrue(lockedMsg.contains("locked out"), "Unexpected message: " + lockedMsg);
                break;

            case "Invalid":
                String invalidMsg = getDriver().findElement(By.cssSelector("[data-test='error']")).getText();
                Assert.assertTrue(invalidMsg.contains("do not match"), "Unexpected message: " + invalidMsg);
                break;
        }
    }

    @Test(priority = 2)
    public void found() {
        logger.info("Navigating to the page");
        getDriver().get(ConfigReader.getProperty("url"));
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.setUsername("standard_user");
        loginpage.setPassword("secret_sauce");
        loginpage.clickLoginButton();

        logger.info("Verifying page title after login...");
        String actualTitle = getDriver().getTitle();

        // Forced failure to test screenshot capture. Remove it when you're done testing.
        Assert.assertEquals(actualTitle, "Forced Error Title", "Intentional mismatch to test screenshot capture");
        // Assert.assertEquals(actualTitle, "Swag Labs");
    }

    @Test(priority = 1)
    public void found1() {
        logger.info("Navigating to the page");
        getDriver().get(ConfigReader.getProperty("url"));
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.setUsername("standard_user");
        loginpage.setPassword("secret_sauce");
        loginpage.clickLoginButton();

        logger.info("Verifying page title after login...");
        String actualTitle = getDriver().getTitle();

        Assert.assertEquals(actualTitle, "Swag Labs");
    }
}