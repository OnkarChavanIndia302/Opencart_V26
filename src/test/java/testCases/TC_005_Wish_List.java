package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.Wish_List_Open;

public class TC_005_Wish_List extends BaseClass{

	@Test
	public void verify_test() {
		try {
			logger.info("tyring to open the wishlist page ");
		
			HomePage hp = new HomePage(driver) ;
			hp.click_wish_list();
						
			LoginPage lp = new LoginPage(driver);
			lp.setmail(p.getProperty("email"));
			lp.setpass(p.getProperty("pass"));
			lp.login_btn();
			
			logger.info("opend the wishlist page ");
			Wish_List_Open wl = new Wish_List_Open(driver) ;
			wl.is_open();			
			logger.info("validating the wish list page...");
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertFalse(false);
		}
		
	}
	
}
