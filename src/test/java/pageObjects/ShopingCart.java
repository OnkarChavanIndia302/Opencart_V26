package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopingCart extends BasePage{

	public ShopingCart(WebDriver driver) {
		super (driver) ;
	}
	
	
	@FindBy(xpath = "//h1[normalize-space()='Shopping Cart']")
	WebElement is_open ;
	
	
	public boolean is_open_display() {
		
	boolean status = is_open.isDisplayed();
	return status;
	
	}
	
	public void close_cart_page() {
		driver.navigate().back();
	}
	
}
