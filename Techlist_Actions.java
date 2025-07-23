package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import WebCommonElement.CommonFunctions;

public class Techlist_Actions 
{
	WebDriver dr=null;
	ExtentTest logger=null;
	Techlist_locators TechL=null;
	public Techlist_Actions(WebDriver dr, ExtentTest logger)
	{
		this.dr=dr;
		this.logger=logger;
		TechL=new Techlist_locators(dr, logger);
	}
	
	public void sendDetails(String username, String password)
	{
		WebElement Fname=TechL.fecthUserName();
		CommonFunctions.enterText(dr, Fname, username, logger, "FirstName");
		
		WebElement Lname=TechL.fecthPassword();
		CommonFunctions.enterText(dr, Lname, password, logger, "LasttName");
	}
}
