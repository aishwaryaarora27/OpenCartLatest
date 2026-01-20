package testCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC002_LoginTest extends BaseTest{
	
	@Test(groups= {"Sanity","Master"})
	public void verifyLogin() {
		
		ITestResult res=Reporter.getCurrentTestResult();
		res.setAttribute("WebDriver", driver);
		
		try {
		logger.info("**Starting TC002_LoginTest**");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage login=new LoginPage(driver);
		login.setEmail(p.getProperty("email"));
		login.setPassword(p.getProperty("password"));
		login.clickLogin();
		
		String title=driver.getTitle();
		Assert.assertEquals(title,"My Account");
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			Assert.fail();
		}
		logger.info("**Finished TC002_LoginTest**");
		
	}

}
