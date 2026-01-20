package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseTest {
	
	@Test(groups= {"Sanity","Regression","Master"})
	public void verifyRegistration() throws InterruptedException {
		
		logger.info("**Starting TC001_AccountRegistrationTest**");
		HomePage hp=new HomePage(driver);
		
		logger.info("**Clicking on MyAccount**");
		hp.clickMyAccount();
		
		logger.info("**Clicking on Register");
		hp.clickRegister();
		
		AccountRegistrationPage accReg=new AccountRegistrationPage(driver);
		
		logger.info("**Providing user's details in the registration form**");
		
		
		accReg.setFirstName(randomAlphabetic(5).toUpperCase());
		accReg.setLastName(randomAlphabetic(4).toUpperCase());
		accReg.setEmail(randomAlphabetic(5)+"@gmail.com");
		accReg.setPhoneNumber(randomNumeric(10));
		String password=randomAlphanumeric(5);
		accReg.setPassWord(password);
		accReg.confirmPassword(password);
		
		accReg.setPolicyChk();
		//Thread.sleep(3000);
		
		logger.info("**Submitting the registration form**");
		accReg.clickContinue();
		
		
		logger.info("**Validating Confirmation Message**");
		String msg=accReg.getConfirmMessage();
		
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		logger.info("**Finished TC001_AccountRegistrationTest**");
		
	}
	
	
}
