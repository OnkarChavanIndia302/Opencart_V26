package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver ) {
		super(driver) ;
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement fname ;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lname ;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email ;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement pass; 

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkbox;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement procedd ;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement getconfirmation ;

	public void setfname(String user) {
		fname.sendKeys(user);
	}

	public void setlname(String surname) {
		lname.sendKeys(surname);
	}

	public void setmail(String mail) {
		email.sendKeys(mail);
	}

	public void setpass(String pwd) {
		pass.sendKeys(pwd);
	}

	public void chkbox_enable() {
		chkbox.click();
	}

	public void presscontinew() {
		procedd.click();
	}

	public String confirm() {
		try {
			return (getconfirmation.getText());
		}
		catch(Exception e ){
			return(e.getMessage()) ;
		}
	}



}


