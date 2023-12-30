/**
 * 
 */
package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.anthem.selenium.SuperHelper;
import com.anthem.selenium.constants.ApplicationConstants;
import com.anthem.selenium.utility.ExtentReportsUtility;
import com.anthem.selenium.utils.DatabaseUtils;
import com.anthem.selenium.utils.SOAPServiceUtils;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


/**
 * @author shiva.katula
 * 
 *         ##### Do not delete this file. ######
 * 
 *         This class is specifically only for any Project specific Helper
 *         Methods for SuperHelper Extension
 *
 *         Any method defined in this class will be automatically available in
 *         the project like Super Helper methods. Any methods defined here will
 *         need to be called without the Class Name to maintain consistency in
 *         calling If Project defines a helper method here and if that method is
 *         harvested back into Selenium Framework by Architects, then the local
 *         method in this class can be deleted with out changing any of the test
 *         scripts.
 * 
 *         To maintain naming convention across all the projects do not name the
 *         method with the Project Name. Also take the suggestion/Approval for
 *         the method name to avoid discrepancy with the Framework methods.
 * 
 */
public class CoreSuperHelper extends SuperHelper {

	// This is the example method to be followed while coding CoreSuperHelper
	// methods.
	/**
	 * <p>
	 * Click web element
	 * </p>
	 * 
	 * @param screenshot Enter True or false to capture snap shot before clicking
	 *                   web element
	 * @param testObject required test object need to test
	 * @param buttonName name Enter buttonName name to click.
	 * @param step       Enter steps to perform
	 * @throws Exception
	 */

	private static boolean testDataName = false;

	public static void seClick1(boolean screenShot, WebElement testObject, String buttonName, String step)
			throws Exception {
		int successFlag = ApplicationConstants.FAIL;

		if (step == null) {
			step = "Click " + buttonName;
		}
		if (testObject.isDisplayed()) {
			testObject.click();
			successFlag = ApplicationConstants.PASS;
			ExtentReportsUtility.log(successFlag, step,
					"Core Super Helper Expected button \"" + buttonName + "\" clicked successfully");

		} else {

			ExtentReportsUtility.log(successFlag, step, "Core Super Helper Unable to click the button \"" + buttonName
					+ "\" successfully" + seCaptureScreenshot(getWebDriver(), buttonName));

		}
		if (screenShot) {
			ExtentReportsUtility.log(successFlag, step, seCaptureScreenshot(getWebDriver(), buttonName));
		}
	}

	public static boolean seSetText(WebElement testObject, String text, String step) {

		try {
			int successFlag = ApplicationConstants.FAIL;

			if (step == null) {
				step = "Enter " + text;
			}
			// if (testObject.isDisplayed()) {
			testObject.sendKeys(text);
			successFlag = ApplicationConstants.PASS;
			ExtentReportsUtility.log(successFlag, step,
					"Core Super Helper-Expected text \"" + text + "\" entered successfully");

			// } else {
			// ExtentReportsUtility.log(successFlag, step, "Core Super Helper-Expected text
			// \"" +text +"\" not entered in \"" + testObject + "\" field"+
			// seCaptureScreenshot(getWebDriver(), text));
			// }

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReportsUtility.log(FAIL, step, seCaptureScreenshot(getWebDriver(), text));
		}
		return isLastTestPassed();
	}

	public static String medicareIDGenerator() {
		String medicareID = null;
		Random random = new Random();
		String generatedString = null;

		medicareID = String.valueOf(random.nextInt(10) + 1);
		generatedString = RandomStringUtils.random(2, true, false);
		medicareID = medicareID + generatedString;
		medicareID = medicareID + String.valueOf(random.nextInt(10) + 1);
		generatedString = RandomStringUtils.random(2, true, false);
		medicareID = medicareID + generatedString;
		medicareID = medicareID + String.valueOf(random.nextInt(10) + 1);
		generatedString = RandomStringUtils.random(2, true, false);
		medicareID = medicareID + generatedString;
		medicareID = medicareID + String.valueOf(random.nextInt(900) + 100);

		return medicareID;

	}

	/**
	 * Overriding method written to select the drop down value using visible text
	 * 
	 * @param element
	 * @param dropdownValue
	 * @param testSummary
	 * @return
	 */
	public static boolean seSelectText(WebElement element, String dropdownValue, String testSummary) {
		try {
			if (isLastTestPassed() || isIgnoreLastTestFailure()) {
				Select dropDown = new Select(element);
				dropDown.selectByVisibleText(dropdownValue);
				Thread.sleep(1000);
				// String selectedValue = seGetDropDownValue(element);
				// if (dropdownValue.equals(selectedValue)) {
				if (isLastTestPassed()) {
					log(PASS, testSummary, "Selected " + dropdownValue + " value from the list");
				} else {
					log(FAIL, testSummary, "Unable to select " + dropdownValue + " value from the list");
					setLastTestPassed(false);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// processException(e, "Exception occured :");
			setLastTestPassed(false);
		}
		return isLastTestPassed();
	}

	protected static String getDate(String format, String day) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		switch (day) {
		case "FirstDayOfMonth":
			int min = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
			calendar.set(Calendar.DAY_OF_MONTH, min);

			break;
		case "LastDayOfMonth":
			int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			calendar.set(Calendar.DAY_OF_MONTH, max);
			break;
		case "Current":

			break;

		default:
			break;
		}

		return sdf.format(calendar.getTime());
	}

	protected static String randomAlphabet(int numberOfChar) {
		String stringValue = "";
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(numberOfChar);
		for (int i = 0; i < numberOfChar; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		stringValue = buffer.toString();

		return stringValue;
	}

	protected static int randomNumber(int numberOfDigit) {
		int number;
		String min = "1";
		String max = "9";
		for (int i = 1; i < numberOfDigit; i++) {
			min = min + 0;
		}
		for (int j = 1; j < numberOfDigit; j++) {
			max = max + 0;
		}
		Random random = new Random();
		number = Integer.valueOf(min) + random.nextInt(Integer.valueOf(max));
		return number;
	}

	protected static String addDaysToDate(String inputDate, int NoOfdays) {
		String tempDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(inputDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, NoOfdays);
			date = cal.getTime();
			tempDate = sdf.format(date);

		} catch (Exception e) {
			e.printStackTrace();
			setResult(false);
		}
		return tempDate;
	}

	protected static boolean deleteExistingFile(File file) {
		if (file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}



	public static void copyFile(File sourceFile, File destionationFile) {
		try {
			InputStream in = new FileInputStream(sourceFile);
			OutputStream out = new FileOutputStream(destionationFile);
			FileUtils.copyFile(sourceFile, out);
			in.close();
			out.close();
		} catch (Exception e) {
			log(FAIL, "Error in copying files from " + sourceFile + " to " + destionationFile);
			e.printStackTrace();
			setResult(false);
		}

	}

	// Change Date Format in requested format
	public static String changeDateFormat(String date, String inFormat, String outFormat) {
		String dateFormatted = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
			Date dob = sdf.parse(date);
			dateFormatted = new SimpleDateFormat(outFormat).format(dob);
		} catch (Exception e) {
			log(FAIL, "Error in changeDateFormat() fn", true);
			e.printStackTrace();
			setResult(false);
		}
		return dateFormatted;
	}

	// Read xml from a file and save as a String
	public static String readXMLFrmFile(File srcFile) {
		String xml = null;
		String inputxml = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(srcFile));
			while ((xml = br.readLine()) != null) {
				if (inputxml != null) {
					inputxml = inputxml + xml + "\n";
				} else {
					inputxml = xml + "\n";
				}
			}
			br.close();
		} catch (Exception e) {
			log(FAIL, "Error in readXMLFrmFile() fn");
			e.printStackTrace();
			setResult(false);
		}
		return inputxml;

	}

	// Get Response status code from SOAP Response
	public static int getSOAPResponseCode(HttpURLConnection con) throws IOException {
		int respCode = con.getResponseCode();
		//		if (respCode == 200) {
		//			log(PASS, "Response code received is: 200");
		//		} else {
		//			log(FAIL, "Response code received is: " + respCode);
		//		}
		return respCode;
	}

	// Get XML Response from SOAP Request
	public static StringBuffer getResponseXML(HttpURLConnection conn) {
		StringBuffer response = new StringBuffer();
		BufferedReader in=null;
		try {
			if((200>=conn.getResponseCode()) && (conn.getResponseCode()<=299)) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}else {
				in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			log(PASS, "SOAP Response: <textarea Style='border:none;'>" + response.toString() + "</textarea>");
			System.out.println(response.toString());

			return response;
		} catch (Exception e) {
			log(FAIL, "Error in getResponseXML() fn", true);
			e.printStackTrace();
			setResult(false);
		}
		return response;
	}

	// Get Element value from SOAP Response
	public static String getEleValueFrmSOAPResp(String responseXML, String tagName) {
		String eleValue=null;
		try {
			// Get Node details from Response

			boolean isPrsnt = false;
			Document doc=SOAPServiceUtils.convertXMLStringtoDocument(responseXML);
			//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//			DocumentBuilder db = dbf.newDocumentBuilder();
			//			InputSource is = new InputSource(new StringReader(responseXML));
			//			Document doc = db.parse(is);
			NodeList nodeList = doc.getElementsByTagName("*");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node =  nodeList.item(i);
				if (node.getNodeName().equalsIgnoreCase(tagName)) {
					eleValue=node.getTextContent();
					log(PASS, "Value of '" + tagName + "' tag is: " + eleValue);
					//setCellValue(tagName, eleValue);
					isPrsnt = true;
					break;
				}
			}
			if (isPrsnt == false) {
				log(FAIL, "Element: " + tagName + "is not present in SOAP Response XML");
			}
		} catch (Exception e) {
			log(FAIL, "Error in getEleValueFrmSOAPResponse() fn");
			e.printStackTrace();
			setLastTestPassed(false);
		}
		return eleValue;
	}

	// Get Element value from SOAP Response
	public static String getEleAttrValueFrmSOAPResp(String responseXML, String tagName, String attr) {
		String eleAttrValue=null;
		try {
			// Get Node details from Response
			boolean isprsnt = false;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(responseXML));
			Document doc = db.parse(is);
			NodeList nodeList = doc.getElementsByTagName("*");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				if (element.getNodeName().equalsIgnoreCase(tagName)) {
					if (element.getAttribute(attr) != null) {
						eleAttrValue=element.getAttribute(attr);
						log(PASS, tagName + " has attribute " + attr + " with value as " + eleAttrValue);
						isprsnt = true;
					} else {
						log(FAIL, tagName + " doesn't contain an attribute " + attr);
						isprsnt = true;
						setLastTestPassed(false);
					}
				}
			}
			if (isprsnt == false) {
				log(FAIL, "Element " + tagName + "is not present in SOAP Response XML", true);
				setLastTestPassed(false);

			}
		} catch (Exception e) {
			log(FAIL, "Error in getEleAttrValueFrmSOAPResp() fn", true);
			e.printStackTrace();
			setLastTestPassed(false);
		}
		return eleAttrValue;
	}

	public static void updateXML(Document doc, File srcFile) {
		try {
			TransformerFactory transformerFac = TransformerFactory.newInstance();
			Transformer trans = transformerFac.newTransformer();
			DOMSource dom = new DOMSource(doc);
			StreamResult result = new StreamResult(srcFile);
			trans.transform(dom, result);
		} catch (Exception e) {
			log(FAIL, "Error in updateXML() fn", true);
			e.printStackTrace();
			setLastTestPassed(false);
		}

	}
	
	public static StringWriter getDocXMLAsStr(Document doc) {
		StringWriter writer=null;
		try {
			
			TransformerFactory transformerFac = TransformerFactory.newInstance();
			Transformer trans = transformerFac.newTransformer();
			DOMSource dom = new DOMSource(doc);
			StreamResult result = new StreamResult(writer);
			trans.transform(dom, result);
		} catch (Exception e) {
			log(FAIL, "Error in updateXML() fn", true);
			e.printStackTrace();
			setLastTestPassed(false);
		}
		return writer;

	}

	public static Document readXMLAsDocument(String xml) {
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			doc = db.parse(is);

		} catch (Exception e) {
			log(FAIL, "Error in readXMLAsDocument() fn " + e.getMessage());
			setLastTestPassed(false);
			e.printStackTrace();
		}
		return doc;
	}

	public static void updateXMLNodeValue(Document doc, File srcFile,String node,String value) {
		boolean flag=false;  
		NodeList nodeList = doc.getElementsByTagName("*");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			if (element.getNodeName().equalsIgnoreCase(node)) {
				element.setTextContent(value);
				log(PASS, "Updated "+node+" node value as " + value);
				flag=true;
				updateXML(doc, srcFile);
				break;
			}
		}
		if(flag==false) {
			log(FAIL, "Error in updating XML node value for: " + node);
			setLastTestPassed(false);

		}
	}

	public static Document updateXMLNodeValue(Document doc,String parentNodes[],String node,String value) {
		boolean flag=false;  
		int temp=0;
		NodeList nodeList = doc.getElementsByTagName("*");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node pNode =  nodeList.item(i);
			if (pNode.getNodeName().contains(parentNodes[temp])) {
				nodeList = nodeList.item(i).getChildNodes();
				i=0;
				if(temp<parentNodes.length)
					temp=temp+1;
			}
			if(temp==parentNodes.length) {	
				for(int j=0;j<nodeList.getLength();j++) {
					Node n =  nodeList.item(j);
					if(n.getNodeName().contains(node)) {
						n.setTextContent(value);
						log(PASS, "Updated Child node "+node+" value present under Parent node "+parentNodes[temp-1]+" as " + value );
						flag=true;
						//commented on 09/01/2020
						//updateXML(doc, srcFile);
						break;
					}
				}
				break;
			}
		}
		if(flag==false) {
			log(FAIL, "Error in updating XML node value for: " + node);
			setLastTestPassed(false);
			doc=null;

		}

		return doc;

	}


	public static boolean verifyQryResSizeInDB(String query, Connection conn) {
		boolean isPrsnt = false;
		try {
			String mbrSrchQry = query;
			ResultSet rs = DatabaseUtils.executeQuery(conn, query);
			if (rs.next()) {
				isPrsnt = true;
			}
		} catch (Exception e) {
			log(FAIL, "Error in verifyQryResSizeInDB() fn " + e.getMessage());
			setLastTestPassed(false);
			e.printStackTrace();
		}
		return isPrsnt;
	}

	public static String getChildEleValue(Document doc_270_file,String[] parents,String child) {
		//		int temp=0;
		//		String childEleValue=null;
		//		NodeList nodeList = doc_270_file.getElementsByTagName("*");
		//		//NodeList nodeList = doc_270_file.getChildNodes();
		//		// get child elements 
		//		for(int i=0;i<nodeList.getLength();i++) {
		//			Node pNode =  nodeList.item(i);
		////			if(pNode.hasChildNodes()) {
		////				nodeList = nodeList.item(i).getChildNodes();
		////				i=0;
		////			}
		//			System.out.println(pNode.getNodeName());
		//			if(pNode.getNodeName().contains(parents[temp])) {
		//				//nodeList = nodeList.item(i).getChildNodes();
		//				//i=0;
		//				if(temp<parents.length)
		//					temp=temp+1;
		//			}
		//			}
		//			if(temp==parents.length) {	
		//				for(int j=0;j<nodeList.getLength();j++) {
		//					Node n =  nodeList.item(j);
		//					
		//					if(n.getNodeName().contains(child)) {
		//						childEleValue=n.getTextContent();
		//						break;
		//					}
		//				}
		//				break;
		//			}
		//
		//		}
		//		
		//		
		int temp=0;
		String childEleValue=null;
		NodeList nodeList = doc_270_file.getElementsByTagName("*");
		// get child elements 
		for(int i=0;i<nodeList.getLength();i++) {
			Node pNode =  nodeList.item(i);
			//			if(pNode.hasChildNodes()) {
			//				nodeList = nodeList.item(i).getChildNodes();
			//				i=0;
			//			}
			System.out.println(pNode.getNodeName());
			if(pNode.getNodeName().contains(parents[temp])) {
				nodeList = nodeList.item(i).getChildNodes();
				i=0;
				if(temp<parents.length)
					temp=temp+1;
			}

			if(temp==parents.length) {	
				for(int j=0;j<nodeList.getLength();j++) {
					Node n =  nodeList.item(j);

					if(n.getNodeName().contains(child)) {
						childEleValue=n.getTextContent();
						break;
					}
				}
				break;
			}
		}



		return childEleValue;

	}

	public static List<List<String>> getAllRowValues(String file,String sheetName,boolean flag){
		
		List<List<String>> data=null;
		List<String> rowData=null;
		try {
		Workbook wb=new XSSFWorkbook(new File(file));
		Sheet sheet=wb.getSheet(sheetName);
		for(int i=0;i<sheet.getLastRowNum();i++) {
			Row row=sheet.getRow(i);
			for(int j=0;j<row.getLastCellNum();j++) {
				Cell cell=row.getCell(j);
				rowData.add(cell.getStringCellValue());
			}
			data.add(rowData);
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return data;
		
		
	}

}
