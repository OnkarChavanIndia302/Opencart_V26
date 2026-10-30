package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver) ;
	}

	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	WebElement lnk_myaccount ;

	@FindBy(xpath = "//a[normalize-space()='Register']") 
	WebElement lnk_register ;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement login_page ;
	
	@FindBy(xpath = "//span[normalize-space()='Shopping Cart']")
	WebElement shoping_link ;
	
	@FindBy(xpath = "//span[normalize-space()='Wish List (0)']")
	WebElement wish_list ;
	
	

	public void clk_myaccount() {
		lnk_myaccount.click();
	}

	public void open_myaccount() {
		lnk_register.click();
	}
	
	public void click_login_link() {
		login_page.click();
	}
	
	public void click_shoping_cart() {
		shoping_link.click();
	}
	
	public void click_wish_list() {
		wish_list.click();
		
	}
}
