package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@name='firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//*[@name='lastname']")
	WebElement lastName;
	
	@FindBy(xpath="//*[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//*[@name='telephone']")
	WebElement phone;
	
	@FindBy(xpath="//*[@name='password']")
	WebElement pwd;
	
	@FindBy(xpath="//*[@name='confirm']")
	WebElement cnfrmPwd;
	
	@FindBy(xpath="//*[@name='agree']")
	WebElement policyChkbox;
	
	@FindBy(xpath="//*[@value='Continue']")
	WebElement submitBtn;

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		firstName.sendKeys(fname);	
		}
	
	public void setLastName(String lname) {
		lastName.sendKeys(lname);	
		}
	
	public void setEmail(String mail) {
		email.sendKeys(mail);	
		}
	
	public void setPhoneNumber(String phn) {
		phone.sendKeys(phn);	
		}
	
	public void setPassWord(String pass) {
		pwd.sendKeys(pass);	
		}
	
	public void confirmPassword(String pass) {
		cnfrmPwd.sendKeys(pass);	
		}
	
	public void setPolicyChk() {
		Actions act=new Actions(driver);
		act.click(policyChkbox).perform();
	}
	
	public void clickContinue() {
		submitBtn.sendKeys(Keys.ENTER); // Simulates pressing the enter key on a keyboard
		}
	
	public String getConfirmMessage() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		try {
		return wait.until(ExpectedConditions.visibilityOf(msgConfirmation)).getText();
		}
		catch(Exception e){
			return e.getMessage();
		}
	}
}
