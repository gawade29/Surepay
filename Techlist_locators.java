package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class Techlist_locators 
{
	WebDriver dr=null;
	ExtentTest logger=null;
	
	public Techlist_locators(WebDriver dr, ExtentTest logger)
	{
		this.dr=dr;
		this.logger=logger;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(name="name") public WebElement UserName;
	@FindBy(name="password") public WebElement Password;

	public WebElement fecthUserName() {
		return UserName;
	}
	public WebElement fecthPassword() {
		return Password;
	}
	
	
}
