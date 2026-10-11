package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	Actions act ;
	
	
	@FindBy(xpath = "//h1[normalize-space()='My Account']")
	WebElement My_acc ;
	
	
	
	@FindBy(xpath = "//span[contains(text(),'My Account')]" )
	WebElement my_acc_logout ;
	
	
	@FindBy(xpath = "(//a[normalize-space()='Logout'])[1]")
	WebElement logout ;
	
	
	public boolean is_login_display() {
		try {
			return (My_acc.isDisplayed());
		}
		catch(Exception e ) {
			return false ;
		}
	}
	
	
	public void logiing_out() {
		logout.click();
	}


	public void acc_click() {
		my_acc_logout.click();
		
		
	}


}
