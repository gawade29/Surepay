package BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import WebCommonElement.CommonFunctions;


public class BaseInitilizer 
{
		public WebDriver dr=null;
		public static ExtentReports report=null;
		public ExtentTest logger=null;
		public String path="C:\\Users\\S Gawade\\eclipse-workspace\\Automation Jan-2024\\Surepay.ndml\\Conf.properties";
		
		@BeforeSuite
		public void beforeSuite() throws IOException
		{
//			java.util.Date date=Calendar.getInstance().getTime();
//			System.out.println(propertyReader(path, "reporterPath")+date+"Test.html");
			report=new ExtentReports(propertyReader(path, "reporterPath")+"Test.html",true);// it concatenates the file path(test Report) and file name and creates final file name. m
		}
		
		@BeforeMethod
		public void beforeMethod(Method testMethodName) throws IOException, Exception
		{
			logger=report.startTest(testMethodName.getName());//
			dr=CommonFunctions.openBrowserWithURL(propertyReader(path, "browser"),"https://pilotasi.paygov.org.in/asi-webapp/#/ticketbooking");// defined browser name and url in property file 
			dr.manage().window().maximize();
			Thread.sleep(5000);
		}
		
		@AfterMethod
		public void afterSuite()
		{
			report.endTest(logger);
			report.flush();
			dr.close();
		}
		
		public static String propertyReader(String path,String key) throws IOException
		{
			Properties prop=new Properties();
			FileInputStream file=new FileInputStream(path);
			prop.load(file);
			return prop.getProperty(key);
		}
		
		public String screenshot(WebDriver dr, String SSname) throws IOException
		{
			TakesScreenshot takeSs=(TakesScreenshot)dr;		//driver takes control to capture SS of website
			File opFile=takeSs.getScreenshotAs(OutputType.FILE);		// it defines the format of output type
			File dest= new File(propertyReader(path, "ScreenshotPath")+SSname+".png");	// it defines on which destination file will gets saved along with SS Name
			FileUtils.copyFile(opFile, dest);// it copies the created output file to destination path 
			return dest.getAbsolutePath();
		}

}
