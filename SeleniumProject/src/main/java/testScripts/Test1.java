package testScripts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utility.BrowserConstants;
import utility.SuperHelper;

/**
 * Hello world!
 *
 */
public class Test1 extends SuperHelper {
	static int runFlagCol = 0;

	
	public static void main(String[] args) {
		try {

			File file = new File("C:\\Datasheets\\InputDataSheet.xlsx");
			Workbook wb = new XSSFWorkbook(file);
			Sheet sheet = wb.getSheet("Data");

			// Start reporting
			initiateReport();
			Row row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().contains("RunFlag")) {
					runFlagCol = i;
					break;
				}
			}
			if (runFlagCol != 0) {
				System.out.println("Run Flag col is present at Column number: " + runFlagCol);

			} else {
				//log(false, "Run Flag col is not present");
				System.out.println("Run Flag col is not present");
				System.exit(0);
			}

			for (int i = 1; i <=sheet.getLastRowNum(); i++) {
					try {
					
					row = sheet.getRow(i);
					// lOAD Row data into map
					excelLoadRowData(sheet, i);
					if(row.getCell(runFlagCol)!=null) {
						if (row.getCell(runFlagCol).toString().equalsIgnoreCase("Yes")) {
							startTest(getCellValue("TCName"));
							
							//Open Browser
							openBrowser(BrowserConstants.chrome);
							WebDriverWait wait=new WebDriverWait(driver, 6);
							driver.get("http://www.google.com");
							driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							//Perform Google search
							driver.findElement(By.name("q")).sendKeys(getCellValue("Input"));
							driver.findElement(By.name("btnK")).click();
							log(true, "Clicked Enter key");
							wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("hdtb-msb"))));
							log(true, "Search Page opened for "+getCellValue("Input"));
							driver.close();
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					endReport();
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			//driver.close();
		}



	}
}
