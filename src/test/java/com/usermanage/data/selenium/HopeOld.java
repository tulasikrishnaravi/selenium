package com.usermanage.data.selenium;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HopeOld {

	WebDriver driver=null;          //we need to have we driver to trigger browser.
	@BeforeMethod
	public void Driver() {
	 driver=new ChromeDriver();
	driver.manage().window().maximize();
	}
	
@Test	
public void found() {                     // by using testng we have ran directly and triggered the browswer and url.
		
		driver.get("https://www.saucedemo.com/");   // to open the link now
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("#login-button")).click();
		
	}
/*
public static void main(String args[]) {  // ran using main method , now i want to run using testng
	Hope hope=new Hope();
	hope.found();
}
*/
}
