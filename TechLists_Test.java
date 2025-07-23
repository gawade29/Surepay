package BaseClass;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.Test;

public class TechLists_Test extends BaseTech
{

	Techlist_Actions TechA=null;
	@Test
	public void TC_01() throws IOException
	{
		TechA=new Techlist_Actions(dr, logger);
		readExcelFile("C:\\Users\\S Gawade\\Documents\\Techlistic.xlsx", "Form");
//		XSSFRow row=sheet.getRow(1);
//		String firstName=row.getCell(0).getStringCellValue();
//		String Lastname=row.getCell(1).getStringCellValue();
		
		TechA.sendDetails(readTestData(1,0),readTestData(1,1));
	}
}
