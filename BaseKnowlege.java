package BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import WebCommonElement.CommonFunctions;

public class BaseKnowlege 
{

	public static WebDriver dr=null;
	public static ExtentReports report=null;
	public static ExtentTest logger=null;
	public String reportPath="C:/Users/S Gawade/Documents/KnowReport.html";
	public static String ssPath="C:/Users/S Gawade/Documents/";
	public static String filePath="C:\\Users\\S Gawade\\Documents\\MyData.xlsx";
	public static String ReadSheetName="Form";
	public  static String WriteSheetName="TCRESULT";
	public  static String TestResultFile="C:\\Users\\S Gawade\\Documents\\TestResultMyData.xlsx";
	public static Method testname=null;
	String fetchTestName=null;
	@BeforeSuite
	public void beforeAllTC()
	{
		report=new ExtentReports(reportPath,true);//true: remove all existing data from  html report 
													//and replaced with new output
													//false: append new output with existing output
	}
	
	@BeforeMethod
	public void beforeEachTc(Method testname)
	{	this.testname=testname;
		logger=report.startTest(testname.getName());//StartTest method require string format. Method class converts in String by getName method
		dr=CommonFunctions.openBrowserWithURL("chrome", "https://www.knowledgeware.in/reg.html");
		fetchTestName=testname.getName();
		System.out.println(fetchTestName);
	}
	
	@AfterMethod
	public void afterEachTC()
	{
		report.endTest(logger);
		report.flush();
		dr.close();
	}
	public static String Screenshot(String SSname) throws IOException
	{
		TakesScreenshot take=(TakesScreenshot)dr;
		File opFile=take.getScreenshotAs(OutputType.FILE);
		File Dest=new File(ssPath+SSname+".jpg");
		FileUtils.copyFile(opFile, Dest);
		return Dest.getAbsolutePath();
	}
	
	public static XSSFWorkbook Readworkbook=null;
	public static XSSFWorkbook WriteWorkbook =null;
	public static XSSFSheet Readsheet,Writesheet=null;
	public static XSSFRow row=null;
	public static XSSFCell ccell=null;
	
	public static void readFile() throws InvalidFormatException, IOException
	{
		File file=new File(filePath);
		Readworkbook=new XSSFWorkbook(file);
		Readsheet=Readworkbook.getSheet(ReadSheetName);		
	}
	
	public static String fetchCellData(int rows, int cells) throws InvalidFormatException, IOException
	{
		readFile();
		XSSFRow row=Readsheet.getRow(rows);
		Cell cell=row.getCell(cells);
		System.out.println(cell);
		DataFormatter formatter = new DataFormatter();
		String cellVal = formatter.formatCellValue(cell);
		return cellVal;
	}
	
	public static Object[][] fetchMultipleData() throws InvalidFormatException, IOException
	{
		readFile();
		int totalRow=Readsheet.getPhysicalNumberOfRows();
		int totalcols=Readsheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(totalRow +"and "+totalcols);
		Object[][] data=new Object[totalRow][totalcols];
		
		for(int i=0;i<totalRow;i++)
		{
			XSSFRow readRow=Readsheet.getRow(i);
			
		for(int j=0;j<totalcols;j++)
		{
			XSSFCell cellVal=readRow.getCell(j);
			CellType type=cellVal.getCellType();
			DataFormatter forma=new DataFormatter();
			data[i][j]=forma.formatCellValue(cellVal);
			System.out.println(data[i][j]);
		}
	}
		return data;	
}
	//copied above testdatafile to another excel file again
	public static void WriteTestResult() throws InvalidFormatException, IOException
	{
		WriteWorkbook=new XSSFWorkbook();
		Writesheet=WriteWorkbook.createSheet("TCRes");
		readFile();
		int totalRow=Readsheet.getPhysicalNumberOfRows();
		int totalcols=Readsheet.getRow(0).getPhysicalNumberOfCells();
		int cell=Readsheet.getRow(0).getLastCellNum()+1;    
		System.out.println(cell);
		//System.out.println(totalRow +"and "+totalcols);
		Object[][] data=new Object[totalRow][totalcols];
		
		for(int i=0;i<totalRow;i++)
		{
			XSSFRow readRow=Readsheet.getRow(i);
			XSSFRow writeRow=Writesheet.createRow(i);
			
		for(int j=0;j<totalcols;j++)
		{
			XSSFCell cellVal=readRow.getCell(j);
			CellType type=cellVal.getCellType();
			DataFormatter forma=new DataFormatter();
			data[i][j]=forma.formatCellValue(cellVal);
			
			XSSFCell writeCell=writeRow.createCell(j);
			Object val=data[i][j];
			
			if(val instanceof String)
					writeCell.setCellValue((String)val);
			if(val instanceof Integer)
				writeCell.setCellValue((Integer)val);
		}
	}
		FileOutputStream file=new FileOutputStream(TestResultFile);
		WriteWorkbook.write(file);
		file.close();
		System.out.println("File Copied Successfully");
}
	
	public static void updateResult(String TestStatus) throws InvalidFormatException, IOException 
	{
		readFile();
		int totalRow=Writesheet.getPhysicalNumberOfRows();
		int totalcols=Writesheet.getRow(0).getPhysicalNumberOfCells();
		
		for(int i=0;i<totalRow;i++)
		{
			XSSFRow row=Writesheet.getRow(i);
			int cell=row.getLastCellNum()+1;  
			XSSFCell createCell=row.createCell(cell);
			createCell.setCellValue(TestStatus);
			
			FileOutputStream file=new FileOutputStream(TestResultFile);
			WriteWorkbook.write(file);
			file.close();
		}
	}
	
	
	
	@DataProvider(name="FormData")
	public static Object[][] dp() throws InvalidFormatException, IOException 
	{
		Object[][] data=fetchMultipleData();
		return data;
	}
	
	
}
