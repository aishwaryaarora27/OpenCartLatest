package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement eMail;
	
	@FindBy(id="input-password")
	WebElement password;
	
	@FindBy(xpath="//*[@value=\"Login\"]")
	WebElement btnLogin;
	
	public void setEmail(String email) {
		eMail.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}

}
