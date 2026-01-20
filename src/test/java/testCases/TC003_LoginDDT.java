package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseTest {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verify_LoginDDT(String email, String password, String res) {
		
		logger.info("**Starting TC003_LoginDDT**");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage accpage=new MyAccountPage(driver);
		String title=driver.getTitle();
		if(res.equalsIgnoreCase("Valid")) 
			{
				if(title.equals("My Account")) {
					accpage.clickLogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
		else {
			if(title.equals("My Account")) {
				accpage.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);	
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("**Finished TC003_LoginDDT");
	}
}
