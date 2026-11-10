package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageOld {
	
	private WebDriver driver;
	private final By username=By.cssSelector("#user-name");
	private final By password=By.cssSelector("#password");
	private final By login=By.cssSelector("#login-button");
	
	public LoginPageOld(WebDriver driver) {
		this.driver=driver;
		
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
