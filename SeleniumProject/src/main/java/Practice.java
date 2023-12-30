import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.record.formula.functions.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.netty.util.Timeout;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Practice {
 public static void main(String[] args) {
	 System.setProperty("webdriver.chrome.driver", "C:\\sdas\\chrome.driver");
	 WebDriver driver=new ChromeDriver();
	 
	 driver.get(url);
	 WebDriverWait wait=new WebDriverWait(driver, TimeUnit.SECONDS);
	 wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	 
	 driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
	 
	 XSSFWorkbook workbook=
			 XSSFSheet sheet=workbook.getSheet(0);
	 for(int i=0;i<sheet.get)
	 Row row=sheet.getRow(1);
			 Cell cell=row.
					 
				Exten			 
					 
					 
					 
	 
 }
}
