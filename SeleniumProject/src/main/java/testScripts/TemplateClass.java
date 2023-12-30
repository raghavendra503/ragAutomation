package testScripts;

import java.io.File;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pages.Home;
import utility.SuperHelper;

public class TemplateClass extends SuperHelper {
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
				// log(false, "Run Flag col is not present");
				System.out.println("Run Flag col is not present");
				System.exit(0);
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				try {

					row = sheet.getRow(i);
					// lOAD Row data into map
					excelLoadRowData(sheet, i);
					if (row.getCell(runFlagCol) != null) {
						if (row.getCell(runFlagCol).toString().equalsIgnoreCase("Yes")) {
							startTest(getCellValue("TCName"));

							/******** Implement your Test case logic here ************/

							
							
							
							/************************************************************/
							driver.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					endReport();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}
}
