package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Wish_List_Open extends BasePage{

	WebDriver driver ;
	public Wish_List_Open(WebDriver driver) {
		super(driver);
	}


	@FindBy(xpath = "//h1[normalize-space()='My Wishlist']")
	WebElement check_wish_open; 


	public void is_open() {
		boolean status = check_wish_open.isDisplayed();
		if(status == true ) {
			Assert.assertTrue(true);
			System.out.println("succesfully opened the page ");
		}
		
		Assert.assertFalse(false);
		System.out.println("failed to open the page ");
	}


}
