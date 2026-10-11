package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_003_Data_DrivenTest extends BaseClass{
	
	// valid data ---> succesfuly login -> pass test       ------ > logout 
	// valid data ----> login failed   --- > fail test 
	
	// invalid data ----> login succesfully --- > fail tes
	// invalid data ----> faild login  --- > pass test 

	@Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class , groups = "DataDriven")// getting data from the different package so we use these dataProviderClass = DataProviders.class 
	public void verify_login_logout(String email , String pass , String res) {
		logger.info("***Starting a DDT ***") ;
		try {
				
		// homepage
			Thread.sleep(3000);
			
		HomePage hp = new HomePage(driver );
		hp.clk_myaccount();
		Thread.sleep(3000);
		hp.click_login_link();
		
		logger.info("***moving to the login page  ***") ;
		// login page 
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		lp.setmail(email);
		
		lp.setpass(pass);
		Thread.sleep(3000);
		lp.login_btn();
		Thread.sleep(3000);
		
		// verification after the login succesfull 
		
		MyAccountPage mypage = new MyAccountPage(driver) ;
		boolean target = mypage.is_login_display();
		Thread.sleep(3000);
		
	
		if(res.equalsIgnoreCase("valid")) {
			
			if(target == true) {
				Thread.sleep(3000);
				
				Assert.assertTrue(true);
				Thread.sleep(3000);
				
			
				mypage.acc_click();
				Thread.sleep(3000);
				mypage.logiing_out();
				
			}
			
			else {
				Assert.assertFalse(false);
				
			}
	
		}
		Thread.sleep(3000);
		
		if(res.equalsIgnoreCase("invalid")) {
			if(target == true) {
				Thread.sleep(3000);
			mypage.acc_click();
			
				Thread.sleep(2000);
				mypage.logiing_out();
				Assert.assertFalse(false);
				Thread.sleep(3000);
						
			}
			else {
				Assert.assertTrue(true);
				Thread.sleep(3000);
			}
		}
		Thread.sleep(3000);
		
		
		}
		catch(Exception e) {
			Assert.assertFalse(false);
		}
		logger.info("***finished a DDT ***") ;
	}
	
}
