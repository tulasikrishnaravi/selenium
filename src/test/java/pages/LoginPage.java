package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	WebDriverWait wait;
	private final By username=By.cssSelector("#user-name");
	private final By password=By.cssSelector("#password");
	private final By login=By.cssSelector("#login-button");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		}
	
	public void setUsername( String text) {
		driver.findElement(username).sendKeys(text);
		 
}
	
	public void setPassword( String text) {
		driver.findElement(password).sendKeys(text);

	}
	
	public void clickLoginButton() {
		
		driver.findElement(login).click();

	}
	
	

}
