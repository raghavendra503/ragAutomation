package utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SuperHelper {
	static ExtentReports reports=null;
	static ExtentTest test=null;
	static Map<String, String> data=null;
	public static WebDriver driver=null;
	public static void initiateReport() {
		File report=new File("C:\\Reports\\Report.html");
		reports =new ExtentReports(report.toString());

	}

	public static void startTest(String testName) {
		test=reports.startTest(testName); 
	}

	public static void log(boolean result, String testResult) {
		if(result)
			test.log(LogStatus.PASS,testResult );
		else
			test.log(LogStatus.FAIL,testResult );
	}
	public static void endReport() {
		reports.flush();
		reports.endTest(test);
	}

	public static void excelLoadRowData(Sheet sheet,int currentRow) {
		Row rowHeader=sheet.getRow(0);
		Row row=sheet.getRow(currentRow);
		data=new HashMap<String,String>();
		String headerCell=null;
		String dataCell=null;
		for(int j=0;j<row.getLastCellNum();j++) {
			headerCell=rowHeader.getCell(j).getStringCellValue();
			if(row.getCell(j)!=null) {
				if(row.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC) {
					dataCell=String.valueOf(row.getCell(j).getNumericCellValue());
				}else {
					dataCell=row.getCell(j).getStringCellValue();
				}
				data.put(headerCell, dataCell);
			}
			else {
				data.put(headerCell, "");
			}
		}


	}

	public static String getCellValue(String header) {
		String value=null;
		for(Map.Entry<String, String> entry: data.entrySet()) {
			if(entry.getKey().equalsIgnoreCase(header)) {
				value=entry.getValue();
				break;
			}
		}
		if(value==null) {
			test.log(LogStatus.FAIL, "Column: "+header+" is not present in Data sheet");
		}


		return value;
	}


	public static WebDriver openBrowser(String browser) {
		try {
			if(browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.IE.driver", "C:\\Selenium\\InternetExplorerDriver.exe");
				driver=new InternetExplorerDriver();
			}else if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromeDriver.exe");
				driver=new ChromeDriver();
			}

		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e.getMessage());
		}
		return driver;
	}


	public static void setText(WebElement element,String inputText) {
		try {
			element.sendKeys(inputText);
			test.log(LogStatus.PASS,"input "+element,"Expected text "+inputText+" entered successfully");
		}catch (Exception e) {
			test.log(LogStatus.FAIL,"input  "+element, "Expected text "+inputText+" not entered successfully");

		}

	}

	public static void performClick(WebElement element) {
		try {
			element.click();
			test.log(LogStatus.PASS,"Click "+element,"Cliked on "+element+" successfully");
		}catch (Exception e) {
			test.log(LogStatus.FAIL,"Click  "+element, "Error in Clicking "+element);

		}

	}


}


