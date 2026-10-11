package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver ) {
		super(driver) ;
	}


	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailAdd ;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwd ;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btnLogin ;
	
	
	public void setmail(String mail) {
		emailAdd.sendKeys(mail);
	}
	
	public void setpass(String pass) {
		passwd.sendKeys(pass);
	}

	public void login_btn() {
		btnLogin.click();
	}
	
	
}
