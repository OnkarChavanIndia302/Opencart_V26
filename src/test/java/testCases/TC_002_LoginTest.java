package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups = {"sanity"})
	public void verify_login() {
		try {

			logger.info("*** Starting Execution***");
			HomePage hp = new HomePage(driver) ;
			hp.clk_myaccount();
			hp.click_login_link();
			

			LoginPage  lp = new LoginPage(driver) ;
			Thread.sleep(2000);
			lp.setmail(p.getProperty("email"));
			Thread.sleep(2000);
			lp.setpass(p.getProperty("password"));
			lp.login_btn();
			logger.info("reached upto the my page ");
			Thread.sleep(2000);
			MyAccountPage mypage = new MyAccountPage(driver) ;
			boolean target = mypage.is_login_display();

			Assert.assertEquals(target,true);
			logger.info("validation is passed ...");
			Thread.sleep(3000);
			mypage.acc_click();
			Thread.sleep(2000);
			mypage.logiing_out();
			logger.info("logout reached and trying to logout...");

			logger.info("***TC_002_login_Finished Execution***");
		}
		catch(Exception e) {
			logger.error("Failed test case", e);
			Assert.fail("Login test failed: " + e.getMessage(), e);


		}
	}
}
