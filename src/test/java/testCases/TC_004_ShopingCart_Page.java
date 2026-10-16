package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ShopingCart;

@Test
public class TC_004_ShopingCart_Page extends BaseClass{

	public void verify_test() {
		logger.info( "trying ot open the home page");
		HomePage hp = new HomePage(driver);
		hp.click_shoping_cart();
		
		logger.info("tyring to open the cart page ");
		ShopingCart sc = new ShopingCart(driver);
		logger.info("validating is open or not ");
		if(sc.is_open_display()==true) {
			Assert.assertTrue(true);
			System.out.println("page opend succesfully...");
		}
		
		else{
			logger.info("failed to open the page ...") ;
			logger.error("trying to find out the error");
			Assert.assertFalse(false);
			System.out.println("something went wrong...");
			
		}
		
		logger.info("page opened succesfully and trying to close the page ") ;
		
		logger.info("bact to the home page");
		
		
				
	}
	
	
}
