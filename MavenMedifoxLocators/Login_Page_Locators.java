package MavenMedifoxLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class Login_Page_Locators 
{
	WebDriver dr=null;
	ExtentTest logger=null;
	public Login_Page_Locators (WebDriver dr, ExtentTest logger) // by using Constructor --> While calling method we just need to create class instance and pass values to method. 1 line of code
																//  create normal method --> while calling method, we need to create instance first and then call method using instance. 2lines of code
	{
		this.dr=dr;
		this.logger=logger;
		PageFactory.initElements(dr, this);	// we are initializing elements here, so therefore added page factory line. 
												//If we have not written this line, getting exception as element is null
	}
	
	@FindBy(name="Username") private WebElement username;
	public WebElement getU()			// this the encapsulation, because no-one should not update any WebElement. i.e limited access to WebElement
	{
		return username;
	}
	
	@FindBy(name="Password") private WebElement password;
	public WebElement getPassword()
	{
		return password;
	}

	@FindBy(xpath = "//input[@type='submit']") private WebElement loginButton;
	public WebElement getLoginbutton()
	{
		return loginButton;
	}
	
	@FindBy(xpath="//li[normalize-space()='Username or Password incorrect']") private WebElement ErrorText;
	public WebElement getErrorText()
	{
		return ErrorText;
	}
	
}
