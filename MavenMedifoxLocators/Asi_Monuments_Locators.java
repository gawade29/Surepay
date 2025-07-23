package MavenMedifoxLocators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class Asi_Monuments_Locators 
{
public WebDriver dr=null;
public ExtentTest logger=null;

public Asi_Monuments_Locators(WebDriver dr, ExtentTest logger)
{
	this.dr=dr;
	this.logger=logger;
	PageFactory.initElements(dr, this);
}
	
@FindBy(xpath="//input[@type='radio' and @value='Mumbai']") private WebElement circleSelect;
public WebElement getCircleSelect() 
{
	return circleSelect;
}

@FindBy(xpath="//div[@id='Shaniwarwada']//input[@type='checkbox']") private WebElement MonuSelect;
public WebElement getMonuSelect() {
	return MonuSelect;
}
@FindBy(xpath ="/html/body/app-root/div/section/app-home-page/div[1]/div[2]/div[3]/div/form/div/div[1]/div/div[4]/div[2]/mat-form-field/div[1]/div/div[2]/mat-select/div/div[1]")
private WebElement soltSelectArrow;
public WebElement getSoltSelectArrow() {
	return soltSelectArrow;
}

@FindBy(xpath="//span[text()='Afternoon (12:00 PM - 06:00 PM)']") private WebElement SlotSelect;
public WebElement getSlotSelect() {
	return SlotSelect;
}

@FindBy(xpath="//span[text()='PROCEED ']") private WebElement Proceed;
public WebElement getProceed() {
	return Proceed;
}
@FindBy(xpath="//input[@placeholder='Enter Name']") private WebElement name;
public WebElement getName() {
	return name;
}
@FindBy(xpath="//input[@placeholder='Enter Age']") private WebElement Age;
public WebElement getAge() {
	return Age;
}
@FindBy(xpath="//input[@placeholder='Enter Email']") private WebElement Email;
public WebElement getEmail() {
	return Email;
}
@FindBy(xpath="//input[@placeholder='Enter Mobile No']") private WebElement mobNo;
public WebElement getMobNo() {
	return mobNo;
}
@FindBy(xpath="//button[text()='PROCEED TO PAY']") private WebElement proccedToPay;

public WebElement getProccedToPay() {
	return proccedToPay;
}

@FindBy(xpath="//div[@id='ICIC']") private WebElement bank;
public WebElement getBank() {
	return bank;
}
@FindBy(xpath="//span[text()='Pay Now']") private WebElement payNow;
public WebElement getPayNow() {
	return payNow;
}
@FindBy(xpath="//button[text()='Success']") private WebElement SuccessButton;
public WebElement getSuccessButton() {
	return SuccessButton;
}

@FindBy(xpath="//button[text()='Failure']") private WebElement FailureButton;
public WebElement getFailureButton() {
	return FailureButton;
}
}
