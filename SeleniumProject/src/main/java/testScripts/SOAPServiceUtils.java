package testScripts;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.anthem.selenium.Utility;

/**
 * This class provides the methods for handling basic soap services functionality.</BR>
 * Please have the  necessary certificate installed to access the services.
 * 
 * @author mayank.nishesh
 *
 */
public class SoapUtils extends Utility  {
	
	/**
	 * <p>
	 *getAndSaveSoapResponse
	 *		This method sends the content from XML File as request 
	 *		and returns the Response as String and save it in a file.
	 *</p>
	 *@param  reqXMLFile
	 * 		Request XML as File Object
	 * @param  elementValue
	 * 		elementValue as a HashMap with xpath as Key and 
	 * 		Value as xpath value to be updated 
	 * @param strEndPointURL
	 * strEndPointURL as the String url	
	 * @param respfilePath
	 *  Filepath to which response needs to be saved
	 */
	

	public static boolean getAndSaveSoapResponse(File reqXMLFile,HashMap <String, String>elementValue,String strEndPointURL,String respfilePath ) 
	{
		
		logExtentReport("get save and SAOP Response from File with update Starts");
		String soapResponse;
		String soapRequest_widtupdte;
		String soapRequest;
		String logmessage;
		boolean issaved = false;
		try {
			soapRequest_widtupdte = convertToSOAPMessage(reqXMLFile);		
			soapRequest = updateSOAPRequest(soapRequest_widtupdte, elementValue);
			soapResponse = getSoapResponse(soapRequest,strEndPointURL);
			issaved = saveSoapMessage(soapResponse,respfilePath);
			logmessage = "getSAOP Response, with update, from File Method Completes Successfully";
		} catch (Exception e) {
			logmessage = "getSAOP Response, with update, from File Method UnSuccessfull";
			soapResponse = null;
			e.printStackTrace();
			issaved = false;
		}
		logExtentReport(logmessage);
		return issaved;
		
	}
	
	/**
	 * <p>
	 *getAndSaveSoapResponse
	 *		This method sends the content from XML File as request 
	 *		and returns the Response as String
	 *</p>
	 *@param  reqXMLFile
	 * 		request as FileObject
	 * @param  strEndPointURL
	 * 		EndPoint url as String
	 * @param filePath
	 *		filePath to which response needs to be saved 
	 */

	public static boolean getAndSaveSoapResponse(File reqXMLFile,String strEndPointURL,String respfilePath ) 
	{
		
		logExtentReport("get And Save SAOP Response from File Starts");
		String soapResponse;
		String soapRequest;
		boolean issaved= false;
		String logmessage;
		try {
			soapRequest = convertToSOAPMessage(reqXMLFile);	
			soapResponse = getSoapResponse(soapRequest,strEndPointURL);
			issaved = saveSoapMessage(soapResponse, respfilePath);
			logmessage = "get And Save SAOP Response from File Ends Successfully";
		} catch (Exception e) {
			logmessage = "get And Save SAOP Response from File is UnSuccessfull";
			soapResponse = null;
			issaved = false;
			e.printStackTrace();
			
		}
		
		logExtentReport(logmessage);
		return issaved;
	}
	
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the SOAPMessage as request 
	 *		and returns the Response as String
	 *</p>
	 *@param  reqXML
	 * 		Request XML as String
	 * @param elementValue
	 * 		elementValue as a HashMap with xpath as Key and 
	 * 		Value as xpath value to be updated 
	 * @param strEndPointURL
	 * strEndPointURL as the String url
	 * @param respFilePath
	 * 		Complete filepath of the file to which response needs to be saved
	 */

	public  static boolean getAndSaveSoapResponse(String reqXML,HashMap <String, String>elementValue,String strEndPointURL,String respFilePath ) 
	{
		
		logExtentReport( "get and save SAOP Response from Request Input String,with Update, Starts");
		String soapResponse;
		String soapRequest;
		String logmessage;
		boolean issaved = false;
		try {
			soapRequest = updateSOAPRequest(reqXML,elementValue);
			soapResponse = getSoapResponse(soapRequest,strEndPointURL);
			issaved = saveSoapMessage(soapResponse, respFilePath);
			logmessage = "get and save SAOP Response from Request Input String with Update Ends Successfully";
		} catch (Exception e) {
			soapResponse = null;
			logmessage = "getSAOP Response from Request Input String with Update is UnSuccessfull";
			issaved= false;
			e.printStackTrace();
		}
		logExtentReport(logmessage);
		return issaved;
	}
	
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the SOAPMessage as request 
	 *		and returns the Response as SOAPMessage
	 *</p>
	 *@param  request
	 * 		request as Soap Message
	 * @param strEndPointURL
	 * 		EndPoint url as String
	 * @param respFilePath
	 * 		Complete filepath of the file to which response needs to be saved
	 * 		
	 */

	public static boolean getAndSaveSoapResponse(SOAPMessage request,String strEndPointURL,String respFilePath ) 
	{
		logExtentReport( "get and Save SoapResponse from SoapMessage Starts");
	
		SOAPMessage soapResponse;
		String soapResponseInString;
		String soapRequest;
		String logmessage;
		boolean issaved = false;
		try {
			soapRequest = convertSOAPMessageToString(request);
			soapResponseInString = getSoapResponse(soapRequest,strEndPointURL);
			soapResponse = convertToSoapMessage(soapResponseInString);
			issaved = saveSoapMessage(soapResponse, respFilePath);
			logmessage = "get and Save SoapResponse from SoapMessage Ends Successfully";
		} catch (Exception e) {
			issaved= false;
			logmessage = "get and Save SoapResponse from SoapMessage is UnSuccessfull";
			e.printStackTrace();
		}
		logExtentReport(logmessage);
		return issaved;
	}
	

	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the SOAPMessage as request 
	 *		and returns the Response as SOAPMessage
	 *</p>
	 *@param  request
	 * 		Request as a Soap Message 
	 * @param elementValue
	 * 		elementValue as a HashMap with xpath as Key and 
	 * 		Value as xpath value to be updated 
	 * @param  strEndPointURL
	 * strEndPointURL as the String url	
	 * @param respFilePath
	 * 		Complete filepath of the file to which response needs to be saved 
	 */
	
	public  static boolean getAndSaveSoapResponse(SOAPMessage request,HashMap <String, String>elementValue,String strEndPointURL,String respFilePath ) 
	{
		
		logExtentReport("get and Save SAOP Response from SOAPMessage with update Starts");
		
		String soapResponseInStrng = null;
		String updateRequest= null;
		SOAPMessage soapResponse= null;
		String logmessage;
		boolean issaved = false;
		try {
			
			updateRequest = updateSOAPRequest(request, elementValue );
			soapResponseInStrng = getSoapResponse( updateRequest,strEndPointURL);
			soapResponse = convertToSoapMessage(soapResponseInStrng);
			issaved = saveSoapMessage(soapResponse, respFilePath);
			logmessage = "get and Save SAOP Response from SOAPMessage with update Completed Successfully";
		} catch (Exception e) {
			logmessage = "get and Save SAOP Response from SOAPMessage with update Fails";
			issaved = false;
			soapResponse = null;
			e.printStackTrace();
		}
		logExtentReport(logmessage);
				return issaved;
	}
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the content from XML File as request 
	 *		and returns the Response as String
	 *</p>
	 *@param  reqXMLFile
	 * 		Request XML as File Object
	 * @param  elementValue
	 * 		elementValue as a HashMap with xpath as Key and 
	 * 		Value as xpath value to be updated 
	 * @param strEndPointURL
	 * strEndPointURL as the String url	 
	 */

	public static String getSoapResponse(File reqXMLFile,HashMap <String, String>elementValue,String strEndPointURL ) 
	{
		logExtentReport("getSAOP Response from File with update Starts");
		String soapResponse;
		String soapRequest_widtupdte;
		String soapRequest;
		try {
			soapRequest_widtupdte = convertToSOAPMessage(reqXMLFile);		
			soapRequest = updateSOAPRequest(soapRequest_widtupdte, elementValue);
			wsDoTrustToCertificates();
			soapResponse = getSoapResponse(soapRequest,strEndPointURL);
		} catch (Exception e) {
			soapResponse = null;
			e.printStackTrace();
		}
		logExtentReport("getSAOP Response with update from File Method Ends");
		return soapResponse;
		
	}
	
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the content from XML File as request 
	 *		and returns the Response as String
	 *</p>
	 *@param  reqXMLFile
	 * 		request as FileObject
	 * @param  strEndPointURL
	 * 		EndPoint url as String

	 */

	public static String getSoapResponse(File reqXMLFile,String strEndPointURL ) 
	{
		logExtentReport("getSAOP Response from File Starts");
		String soapResponse;
		String soapRequest;
		try {
			soapRequest = convertToSOAPMessage(reqXMLFile);	
			wsDoTrustToCertificates();
			soapResponse = getSoapResponse(soapRequest,strEndPointURL);
		} catch (Exception e) {
			soapResponse = null;
			e.printStackTrace();
		}
		logExtentReport("getSAOP Response from File Ends");
		return soapResponse;
	}
	
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the SOAPMessage as request 
	 *		and returns the Response as String
	 *</p>
	 *@param  reqXML
	 * 		Request XML as String
	 * @param elementValue
	 * 		elementValue as a HashMap with xpath as Key and 
	 * 		Value as xpath value to be updated 
	 * @param strEndPointURL
	 * strEndPointURL as the String url	 
	 */

	public static String getSoapResponse(String reqXML,HashMap <String, String>elementValue,String strEndPointURL ) 
	{
		logExtentReport("getSAOP Response from Request Input String,with Update, Starts");
		String soapResponse;
		String soapRequest;
		try {
			soapRequest = updateSOAPRequest(reqXML,elementValue);
			wsDoTrustToCertificates();
			soapResponse = getSoapResponse(soapRequest,strEndPointURL);
		} catch (Exception e) {
			soapResponse = null;
			e.printStackTrace();
		}
		logExtentReport("getSAOP Response from Request Input String,with Update, Ends");
		return soapResponse;
	}
	
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the SOAPMessage as request 
	 *		and returns the Response as SOAPMessage
	 *</p>
	 *@param  request
	 * 		request as Soap Message
	 * @param strEndPointURL
	 * 		EndPoint url as String

	 */

	public static SOAPMessage getSoapResponse(SOAPMessage request,String strEndPointURL ) 
	{
		logExtentReport("getSoapResponse from SoapMessage Starts");
		SOAPMessage soapResponse;
		String soapResponseInString;
		String soapRequest;
		try {
			soapRequest = convertSOAPMessageToString(request);
			wsDoTrustToCertificates();
			soapResponseInString = getSoapResponse(soapRequest,strEndPointURL);
			soapResponse = convertToSoapMessage(soapResponseInString);
		} catch (Exception e) {
			soapResponse = null;
			e.printStackTrace();
		}
		logExtentReport("getSoapResponse from SoapMessage Ends");
		return soapResponse;
	}
	
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the SOAPMessage as request 
	 *		and returns the Response as SOAPMessage
	 *</p>
	 *@param  request
	 * 		Request as a Soap Message 
	 * @param elementValue
	 * 		elementValue as a HashMap with xpath as Key and 
	 * 		Value as xpath value to be updated 
	 * @param  strEndPointURL
	 * strEndPointURL as the String url	 
	 */
	
	public static SOAPMessage getSoapResponse(SOAPMessage request,HashMap <String, String>elementValue,String strEndPointURL ) 
	{
		logExtentReport("getSAOP Response from SOAPMessage with update Starts");
		String soapResponseInStrng = null;
		String updateRequest= null;
		SOAPMessage soapResponse= null;
		try {
			
			updateRequest = updateSOAPRequest(request, elementValue );
			wsDoTrustToCertificates();
			soapResponseInStrng = getSoapResponse( updateRequest,strEndPointURL);
			soapResponse = convertToSoapMessage(soapResponseInStrng);
		} catch (Exception e) {
			soapResponse = null;
			e.printStackTrace();
		}
		logExtentReport("getSAOP Response from SOAPMessage with update Ends");
		return soapResponse;
	}
	
	/**
	 * <p>
	 *getSoapResponse
	 *		This method sends the String as request 
	 *		and returns the Response as String
	 *</p>
	 *@param  request
	 * 		Request Soap Message or XML as String
	 * @param url
	 * 		EndPoint url as String
	 */

	public static String getSoapResponse(String request, String url) throws Exception {
	logExtentReport("Establishing the Connection with Url , Sending the String Request and fetching the Response");
		String response = null;
		URL Url = new URL(url);
		URLConnection connection = Url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		String strInput = request;
		byte[] buffer = new byte[strInput.length()];
		buffer = strInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		String SOAPAction = "";
		// Set the appropriate HTTP parameters.
		wsDoTrustToCertificates();
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		wsDoTrustToCertificates();
		OutputStream out = httpConn.getOutputStream();
		// Write the content of the request to the outputstream of the HTTP
		// Connection.
		out.write(b);
		out.close();
		// Ready with sending the request.
		logExtentReport(Integer.toString(httpConn.getResponseCode())+" Response Retrieved from the Url");
		// Read the response.
		InputStreamReader isr = null;
		System.out.println(Integer.toString(httpConn.getResponseCode()) + "ResponseCode");
		if (httpConn.getResponseCode() == 200) {
			wsDoTrustToCertificates();
			isr = new InputStreamReader(httpConn.getInputStream());
			logExtentReport("Valid Response Recieved from the Url");	
		} else {
			isr = new InputStreamReader(httpConn.getErrorStream());
			logExtentReport("Error Response Recieved from the Url");
		}

		response = convertInputStreamReaderToString(isr);
		return response;
	}

	/**
	 * <p>
	 *convertToSOAPMessage
	 *		This method takes XML File as  Input
	 *		and converts FILE content to the SOAP Message
	 *</p>
	 * @param xmlFile
	 * 		 File Object
	 * 		need to pass the full path for the fileobject
	 * 		Ex:- "C://Application//input.xml
	 */


	public static String convertToSOAPMessage(File xmlFile) throws Exception {
		logExtentReport("Convet XML File content to a string soap message");
		String soapMessage = null;
		String ext = null;
		String filename;
		filename = xmlFile.getName().toString();
		ext = FilenameUtils.getExtension(filename);
		if (ext.equals("xml"))
		{
			FileInputStream fis = new FileInputStream(xmlFile);
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage message = factory.createMessage(new MimeHeaders(), fis);
			soapMessage = convertSOAPMessageToString(message);
			log(PASS, "XML File Found", "Convert To Soap Request Successfull");
		}
		else
		{
			log(FAIL, "XML File not Found", "Convert To Soap Request UnSuccessfull");
			soapMessage = null;
		}
		return soapMessage;
	} 
	
	/**
	 * <p>
	 *updateSOAPRequest
	 *		This method takes SOAPMessage as Input and update the value for field
	 *		based on xpath
	 *</p>
	 *  @param request
	 * 		Soap Request in the from of SOAPMessage
	 * 		
	 * @param elementValue
	 *            Test data and Xpath combination in hmap 
	 *            with xpath as Key
	 */

	@SuppressWarnings("rawtypes")
	public static String updateSOAPRequest(SOAPMessage request, HashMap <String, String>elementValue ) throws Exception {
		logExtentReport("Updating the Values of SOAPMessage as Request stored in a String with HashMap Element Value");
		String soapMessage = null;
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		SOAPMessage message = request;
		SOAPPart msgPart = message.getSOAPPart();
		SOAPEnvelope envelope = msgPart.getEnvelope();
		SOAPBody soapBody = envelope.getBody();
		Set set = elementValue.entrySet();		
		Iterator iterator = set.iterator();		       
		while(iterator.hasNext()) {
			Map.Entry Xpath = (Map.Entry)iterator.next();
			NodeList elements = (NodeList)xPath.compile(Xpath.getKey().toString()).evaluate(soapBody, XPathConstants.NODESET);
			elements.item(0).setTextContent(Xpath.getValue().toString());
		}
		soapMessage = convertSOAPMessageToString(message);
		return soapMessage;

	}


	/**
	 * <p>
	 *updateSOAPRequest
	 *		This method takes XML string as Input and update its value
	 *		based on xpath
	 *</p>
	 * @param request
	 * 		Soap Request XML as the String
	 * 		
	 * @param elementValue
	 *            Test data and Xpath combination in hmap 
	 *            with xpath as Key and xpathvalue as Value
	 */

	@SuppressWarnings("rawtypes")
	public static String updateSOAPRequest(String request, HashMap <String, String>elementValue ) throws Exception {
		logExtentReport("Updating the Values of Request XML stored in a String with values  HashMap Element Value");
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		SOAPMessage message = convertToSoapMessage(request);
		SOAPPart msgPart = message.getSOAPPart();
		SOAPEnvelope envelope = msgPart.getEnvelope();
		SOAPBody soapBody = envelope.getBody();
		Set set = elementValue.entrySet();
		String soapMessage = null;
		Iterator iterator = set.iterator();		       
		while(iterator.hasNext()) {
			Map.Entry Xpath = (Map.Entry)iterator.next();
			NodeList elements = (NodeList)xPath.compile(Xpath.getKey().toString()).evaluate(soapBody, XPathConstants.NODESET);
			elements.item(0).setTextContent(Xpath.getValue().toString());
		}
		soapMessage = convertSOAPMessageToString(message);
		return soapMessage;

	}


	/**
	 * <p>
	 *saveSoapMessage
	 *		This methods Save the SOAPMessage in the specified location
	 *</p>
	 * @param  soapmessage
	 * 		String which needs to be written to the File
	 * @param  filePath
	 * 		Has to be Full File path(with extension) where response needs to be saved	
	 */
	
	public static boolean saveSoapMessage(String soapmessage,String filePath)
	{
		logExtentReport("Storing the Soap Message in Specified File");
		boolean result = false;
		soapmessage = soapmessage.replaceAll("&lt;", "<");
		soapmessage = soapmessage.replaceAll("&gt;", ">");
		soapmessage = soapmessage.replaceAll("&quot;", "\"");
		soapmessage = soapmessage.replaceAll(">", ">\n");
		String text = soapmessage;
		String filename = filePath;
		File file = new File(filename);

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			String bytes = text;
			byte[] buffer = bytes.getBytes();
			FileOutputStream outputStream =
					new FileOutputStream(filename);
			outputStream.write(buffer);
			outputStream.close();  
			result = true;
		}   

		catch (IOException e) {

			e.printStackTrace();
			result = false;
		}

		return result;
	}

	/**
	 * <p>
	 *saveSoapMessage
	 *		This methods Save the SOAPMessage to the specified location
	 *</p>
	 * @param  message
	 * 		SOAP Message which needs to be written to the File
	 * @param  filePath
	 * 		Has to be Full File path(with filename) where SoapMessage needs to be saved
	 * 		
	 */

	public static boolean saveSoapMessage(SOAPMessage message,String filePath)
	{
		logExtentReport("Storing the Soap Message in Specified File when the message is in the form of SOAPMessage");
		String soapMessage = null;
		boolean result = false;
		try {
			soapMessage = convertSOAPMessageToString(message);
			result = saveSoapMessage(soapMessage,filePath);

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>
	 *convertToSoapMessage
	 *		This method takes StringXML as Input and convert it to SOAPMessage
	 *</p>
	 *  * @param  XML
	 * 		input String as XML		
	 */

	public static SOAPMessage convertToSoapMessage(String xml) throws IOException, SOAPException
	{
		logExtentReport("Creating Soap Message from String");
		MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
		MimeHeaders headers = new MimeHeaders();
		SOAPMessage message = null;	
		headers.addHeader("Content-Type", "text/xml");
		ByteArrayInputStream var = new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8")));			 
		message = factory.createMessage(headers, var);		    
		return message;

	}

	/**
	 * <p>
	 *convertSOAPMessageToString
	 *		This method  converts SOAPMessage to String
	 *</p>
	 *  * @param Message
	 * 		as SoapMessage 		
	 */

	public static String convertSOAPMessageToString(SOAPMessage message ) throws Exception {
	logExtentReport("Converting  SOAP Message to String");
		SOAPMessage soapmessage = message;
		String SoapMessage;	 	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		soapmessage.writeTo(baos); 
		SoapMessage = baos.toString();
		return SoapMessage;
	}



	/**
	 * <p>
	 *convertInputStreamReaderToString
	 *		This method takes converts InputStreamReader to String
	 *</p>
	 *  * @param message
	 * 		message in the form of InputStreamReader		
	 */
	
	private static String convertInputStreamReaderToString(InputStreamReader message) throws Exception, SOAPException {

	logExtentReport("Converting InputStreamReader soapMessage to String");
		InputStreamReader isr = message;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		String soapMessage = null;
		try{
			br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				sb.append(line);

			}

		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		soapMessage = sb.toString();
		soapMessage = soapMessage.replaceAll("&lt;", "<");
		soapMessage = soapMessage.replaceAll("&gt;", ">");
		soapMessage = soapMessage.replaceAll("&quot;", "\"");
		return soapMessage;
	}

	/**
	 * <p>
	 *convertResponseStringtoDocument
	 *		This method converts the String XML  to Document  
	 *		to enable XPath actions
	 *</p>
	 * @param  xml
	 * 		xml as String
	 */

	private static Document convertResponseStringtoDocument(String xml)
	{
		logExtentReport("convert Response String to Document");
		String xML = xml;
		Document xmlDocument = null;
		try{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			xmlDocument = builder.parse(new ByteArrayInputStream(xML.getBytes()));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();}
		catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return xmlDocument;
	}

	/**
	 * <p>
	 *getNodes
	 *		This method evaluates the XML passed,as String, against input xpath and 
	 *		returns all the nodes as NodeList
	 *</p>
	 * @param  xml
	 * 		XML as String
	 * @param xpathExpression
	 * 		xpathExpression as String
	 */

	public static NodeList getNodes(String xml,String xpathExpression)
	{
		logExtentReport("Getting the List of Nodes from matching Xpath from String XML");
		XPath xPath =  XPathFactory.newInstance().newXPath();
		Document xmlString = convertResponseStringtoDocument(xml);
		String expression = xpathExpression;
		NodeList nodelist = null;
		try {
			nodelist = (NodeList) xPath.compile(expression).evaluate(xmlString, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {

			e.printStackTrace();
		}


		return nodelist;
	}
	
	/**
	 * <p>
	 *getNodes
	 *		This method evaluates the SOAPMessage against provided xpath and
	 *		returns all the nodes as NodeList
	 *</p>
	 * @param  message
	 * 		message as SOAPMessage
	 * @param xpathExpression
	 * 		XpathExpression as String
	 */

	public static NodeList getNodes(SOAPMessage message,String xpathExpression) throws Exception
	{
	logExtentReport("Getting the List of Nodes from matching Xpath from SOAPMessage ");
		String inputXml = null;

		NodeList nodelist = null;
		try {
			inputXml = convertSOAPMessageToString(message);
			nodelist = getNodes(inputXml, xpathExpression);

		} catch (XPathExpressionException e) {

			e.printStackTrace();
		}


		return nodelist;
	}
	
	/**
	 * <p>
	 *getNodes
	 *		This method performs action on the input XML File and
	 *		returns all the nodes as NodeList matching xpath expression
	 *		
	 *</p>
	 * @param  xmlFile
	 * 		XMLFile as File with .xml extension
	 * @param xpathExpression
	 * 		XpathExpression as String
	 */
	public static NodeList getNodes(File xmlFile,String xpathExpression) throws Exception
	{
		logExtentReport("Getting the List of Nodes from matching Xpath from XML File");
		String message = null;
		NodeList nodelist = null;
		try {
			message = convertToSOAPMessage(xmlFile);		
			nodelist = getNodes(message, xpathExpression);

		} catch (XPathExpressionException e) {
			nodelist = null;
			e.printStackTrace();
		}


		return nodelist;
	}
	
	/**
	 * <p>
	 *getAttributeValue
	 *		This method returns the attribute value of the node
	 *		xpathExpression is evaluated on the String XML
	 *</p>
	 * @param  xml
	 * 		xml as String
	 * @param xpathExpression
	 * 		XpathExpression as String
	 * @param attribute
	 * 		value of node Attribute ( ex:- Text, value)
	 */

	public static String getAttributeValue(String xml,String xpathExpression,String attribute)
	{
		logExtentReport("Getting Attribute Value from Xpath when input value is xml String");
		NodeList Nodelist = getNodes(xml,xpathExpression);
		String Value = null;
		for (int i = 0; i < Nodelist.getLength(); i++) {
			Element element1 = (Element) Nodelist.item(i);
			Value = element1.getAttribute(attribute);
		}
		return Value;

	}
	
	/**
	 * <p>
	 *getAttributeValue
	 *		This method returns the attribute value of the node
	 *		xpathExpression is evaluated on the Input SOAP Message
	 *</p>
	 * @param  message
	 * 		message type is SoapMessage
	 * @param xpathExpression
	 * 		Xpath Expression as String
	 * @param attribute
	 * 		value of node Attribute ( ex:- Text, value)
	 */

	public static String getAttributeValue(SOAPMessage message,String xpathExpression,String attribute)
	{
		logExtentReport("Getting Attribute Value from Xpath when input value is SOAPMessage");
		NodeList Nodelist;
		String value = null;
		try {
			Nodelist = getNodes(message,xpathExpression);
			for (int i = 0; i < Nodelist.getLength(); i++) {
				Element element1 = (Element) Nodelist.item(i);
				value = element1.getAttribute(attribute);
			}
		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return value;

	}
	
	/**
	 * <p>
	 *getAttributeValue
	 *		This method returns the attribute value of the node
	 *		xpathExpression is evaluated on the Input XMLFile
	 *</p>
	 * @param  xmlFile
	 * 		xmlFile File must have extension of .xml
	 * @param xpathExpression
	 * 		Xpath Expression as String
	 * @param attribute
	 * 		value of node Attribute ( ex:- Text, value)
	 */
	public static String getAttributeValue(File xmlFile,String xpathExpression,String attribute ) 
	{
		logExtentReport("Getting Attribute Value from Xpath when input value is XMLFile");
		String value = null;
		String soapmessage = null;
		try {
			soapmessage = convertToSOAPMessage(xmlFile);
			value = getAttributeValue(soapmessage, xpathExpression, attribute);
		} catch (Exception e) {
			value = null;
			e.printStackTrace();
		}


		return value;
	}
	
	/**
	 * <p>
	 *wsDoTrustToCertificates
	 *		This method establishes the secure connection
	 */

	@SuppressWarnings("restriction")
	static public void wsDoTrustToCertificates() throws Exception {
		logExtentReport("wsDoTrustToCertificates Starts");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
						return;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
						return;
					}
				}
		};

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
					System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
				}
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
		logExtentReport("wsDoTrustToCertificates Ends");
	} 

}