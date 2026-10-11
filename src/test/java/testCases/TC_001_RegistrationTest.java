package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_001_RegistrationTest extends BaseClass {

	@Test(groups = {"regression","master"})
	
	
	public void verify_test() throws InterruptedException {
		try {
			logger.info("***Starts a test case***");
			
			HomePage hp = new HomePage(driver) ;
			
			logger.info("***opening the home page ***");
			hp.clk_myaccount();
			
			Thread.sleep(4000);
			logger.info("***opening my acc***");
			hp.open_myaccount();		

			logger.info("***opening registration page ***");
			AccountRegistrationPage obj = new AccountRegistrationPage(driver) ;
			logger.info("***passes the user information***");
			obj.setfname(generaterandom_text());
			obj.setlname(generaterandom_text());
			obj.setmail(generaterandom_text()+"@gmail.com");		
			obj.setpass(generaterandom_pass());
			Thread.sleep(5000);
			obj.chkbox_enable();
			obj.presscontinew();
			logger.info("***getting confirmation message ***");
			String getconfirmation = obj.confirm();
			Assert.assertEquals(getconfirmation,"Your Account Has Been Created!");

		}
		catch(Exception e) {
			logger.error("test case failed...") ;	
			Assert.fail();	
		}
		
		logger.info("***test case passed succesfully...***");
	}

}
