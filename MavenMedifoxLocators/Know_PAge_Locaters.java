package MavenMedifoxLocators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class Know_PAge_Locaters 
{
	public WebDriver dr=null;
	public ExtentTest logger=null;
	
	public Know_PAge_Locaters(WebDriver ConstDr, ExtentTest ConstLogger)
	{
		this.dr=ConstDr;
		this.logger=ConstLogger;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(name="First_Name") public WebElement firstName;
	@FindBy(name="Last_Name") public WebElement LastName;
	@FindBy(name="Birthday_day") public WebElement SelectDAY;
	@FindBy(name="Birthday_Month") public WebElement SelectMONTH;
	@FindBy(name="Birthday_Year") public WebElement SelectYEAR;
	@FindBy(name="Email_Id") public WebElement Email;
	@FindBy(name="Mobile_Number") public WebElement MobNumber;
	@FindBy(xpath ="//input[@value='Male']") public WebElement SelectMale;
	@FindBy(xpath ="//input[@value='Female']") public WebElement SelectFemale;
	

	@FindBy(xpath="//a[text()='NEXT']") public WebElement NextButton;

	public WebElement getFirstName() 
	{
		return firstName;
	}
	public WebElement getLastName() {
		return LastName;
	}
	public WebElement getSelectDAY() {
		return SelectDAY;
	}
	public WebElement getSelectMONTH() {
		return SelectMONTH;
	}
	public WebElement getSelectYEAR() {
		return SelectYEAR;
	}
	public WebElement getEmail() {
		return Email;
	}
	public WebElement getMobNumber() {
		return MobNumber;
	}
	public WebElement getSelectMale() {
		return SelectMale;
	}
	public WebElement getSelectFemale() {
		return SelectFemale;
	}
	public WebElement getNextButton() {
		return NextButton;
	}
}
