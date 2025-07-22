package BaseClass;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import WebCommonElement.CommonFunctions;

public class BaseTech 
{
		public WebDriver dr=null;
		public ExtentReports report=null;
		public ExtentTest logger=null;
		public ExtentTest test=null;
		public XSSFRow row=null;
		public String path="C:\\Users\\S Gawade\\eclipse-workspace\\Automation Jan-2024\\Surepay.ndml\\Conf.properties";
		
		@BeforeSuite
		public void beforeSuite() throws IOException
		{
//			java.util.Date date=Calendar.getInstance().getTime();
//			System.out.println(propertyReader(path, "reporterPath")+date+"Test.html");
			report=new ExtentReports(propertyReader(path, "reporterPath")+"Test.html",true);// it concatenates the file path and file name and creates final file name. m
		}
		
		@BeforeMethod
		public void beforeMethod(Method testMethodName) throws IOException
		{
			logger=report.startTest(testMethodName.getName());
			dr=CommonFunctions.openBrowserWithURL("chrome","https://www.knowledgeware.in/final.html");// defined browser name and url in property file 
		}
		
		@AfterSuite
		public void afterSuite()
		{
			report.endTest(logger);
			report.flush();
		}
		
		public static String propertyReader(String path,String key) throws IOException
		{
			Properties prop=new Properties();
			FileInputStream file=new FileInputStream(path);
			prop.load(file);
			return prop.getProperty(key);
		}
		
		public void screenshot(WebDriver dr, String SSname) throws IOException
		{
			TakesScreenshot takeSs=(TakesScreenshot)dr;		//driver takes control to capture SS of website
			File opFile=takeSs.getScreenshotAs(OutputType.FILE);		// it defines the format of output type
			File dest= new File(propertyReader(path, "ScreenshotPath")+SSname+".png");	// it defines on which destination file will gets saved along with SS Name
			FileUtils.copyFile(opFile, dest);// it copies the created output file to destination path 
			dest.getAbsolutePath();
		}
		public XSSFWorkbook workbook=null;
		public XSSFSheet sheet=null;
		public FileInputStream file=null;;
		
		public void readExcelFile(String fileName, String SheetName) throws IOException
		{
			file=new FileInputStream(fileName);
			workbook=new XSSFWorkbook(file);
			sheet=workbook.getSheet(SheetName);
		}
		
		public String readTestData(int rowIndex, int colIndex)	//1st way to read data by passing rowIndex and ColIndex for each element
		{
		row=sheet.getRow(rowIndex);
		String elementValue=row.getCell(colIndex).getStringCellValue();
		return elementValue;
		}
		public void readfilewithmultipleData(String fileName, String SheetName) throws IOException
		{
			readExcelFile(fileName, SheetName);
			int row=sheet.getLastRowNum();
			int col=sheet.getRow(0).getLastCellNum();
		}
}




