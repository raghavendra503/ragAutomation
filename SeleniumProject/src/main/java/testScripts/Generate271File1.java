
package testScripts;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import utility.CoreSuperHelper;

public class Generate271File1 extends CoreSuperHelper{
	

	public static void main(String[] args) {
	
		String currentWindow=null;
		String xmlPart1=null,xmlPart2=null,xmlPart3=null;
		String targetXML=null,sourceXML=null;
		File File270 = new File("C:\\Users\\lvsrgupta.kesavarapu\\Downloads\\Data\\1365195_20191220080658_FL_o.T_270_x279a1.xml");
		File File271_Template = new File("C:\\Users\\lvsrgupta.kesavarapu\\Downloads\\Data\\271Templates\\FL\\AAA\\209492_20190823037628_FL_o.T_271_x279a1.xml");
		File File271 = new File("C:\\Users\\lvsrgupta.kesavarapu\\Downloads\\Data"+ getCellValue("Template271File")+".xml");
		//Copy 271 Template into Input path
		copyFile(File271_Template, File271);
		// Read 270 XML from file into a string
		sourceXML = readXMLFrmFile(File270);

		// Read 271 Template XML from file into a string
		targetXML = readXMLFrmFile(File271);
		String temp, subTag,dest2000CBody;
		//System.out.println(targetXML);
		String tempStr=targetXML.split("_2000C>")[0];
		int pos=tempStr.lastIndexOf("<")+1;
		String tag2000C=tempStr.substring(pos,tempStr.length());
		tag2000C=tag2000C.concat("_2000C>");
		dest2000CBody="<"+tag2000C+(targetXML.split("<"+tag2000C)[1]);
		dest2000CBody=dest2000CBody.split("</"+tag2000C)[0];
		dest2000CBody=dest2000CBody.concat("</"+tag2000C);
		xmlPart1=targetXML.split("<"+tag2000C)[0];
		xmlPart3=targetXML.split("</"+tag2000C)[1];

		//parse 270 source XML to retrive 2000c tag values
		do {
			tag2000C=tag2000C.replace("271", "270");
			String src2000CBody="<"+tag2000C+(sourceXML.split("<"+tag2000C)[1]);
			src2000CBody=src2000CBody.split("</"+tag2000C)[0];
			src2000CBody=src2000CBody.concat("</"+tag2000C);
			//update 270 file xml string to remove 2000C tag for current member
			sourceXML.replaceFirst(src2000CBody, "");
			Document doc_270_2000C=SOAPServiceUtils.convertXMLStringtoDocument(src2000CBody);
			List<List<String>> dataRules=ExcelUtils.getAllRowValues(EnvHelper.getValue("filePath") + "\\270&271Files\\Generate271File_Rules.xlsx","Rules" , true);
			for(int i=0;i<dataRules.size();i++) {
				String parentTags[]=null;
				String childTags[]=null;
				String value=null;
				if(dataRules.get(i).get(0).equalsIgnoreCase("Member Not Found")) {
					parentTags=dataRules.get(i).get(3).toString().split(",");
					childTags=dataRules.get(i).get(4).toString().split(",");
					
					
					
					//Document doc=SOAPServiceUtils.convertXMLStringtoDocument(xml);
					
					if(dataRules.get(i).get(2).trim().equalsIgnoreCase("270")) {
						for(int j=0;j<childTags.length;j++) {
							if(!(dataRules.get(i).get(3).toString().contains("2000C"))){
								Document doc_271_file=readXMLAsDocument(targetXML);
								String child =getChildEleValue(doc_270_2000C,parentTags,childTags[j]);
								System.out.println(child);
								//Update XML nodevalue 
								Document updDoc=updateXMLNodeValue(doc_271_file,parentTags,childTags[j],child);
								if(updDoc!=null) {
									StringWriter updXML=getDocXMLAsStr(updDoc);
									
								}
							}
						}
						}else {
							if(getCellValue("Segment").equalsIgnoreCase("AAA")) {
								value=dataRules.get(i).get(4).toString();
								for(int j=0;j<childTags.length;j++) {
									//Update XML nodevalue 
									Document updDoc=updateXMLNodeValue(doc_271_file,parentTags,childTags[j],value);
									if(updDoc!=null) {
										StringWriter updXML=getDocXMLAsStr(updDoc);
										if(updXML!=null){
											if(xmlPart2==null)
												xmlPart2=updXML.toString();
											else
												xmlPart2=xmlPart2+updXML.toString();
											//Document cpmplteDoc=readXMLAsDocument(xmlPart1+xmlPart2+xmlPart3);
										}
									}
					
								
								
								
							}else {
							Document doc_271_file=readXMLAsDocument(dest2000CBody);
							String child =getChildEleValue(doc_270_2000C,parentTags,childTags[j]);
							System.out.println(child);
							//Update XML nodevalue 
							Document updDoc=updateXMLNodeValue(doc_271_file,parentTags,childTags[j],child);
							if(updDoc!=null) {
								StringWriter updXML=getDocXMLAsStr(updDoc);
								if(updXML!=null){
									if(xmlPart2==null)
										xmlPart2=updXML.toString();
									else
										xmlPart2=xmlPart2+updXML.toString();
									//Document cpmplteDoc=readXMLAsDocument(xmlPart1+xmlPart2+xmlPart3);
								}
							}
						}
					}else {
						if(getCellValue("Segment").equalsIgnoreCase("AAA")) {
							value=dataRules.get(i).get(4).toString();
							for(int j=0;j<childTags.length;j++) {
								//Update XML nodevalue 
								Document updDoc=updateXMLNodeValue(doc_271_file,parentTags,childTags[j],value);
								if(updDoc!=null) {
									StringWriter updXML=getDocXMLAsStr(updDoc);
									if(updXML!=null){
										if(xmlPart2==null)
											xmlPart2=updXML.toString();
										else
											xmlPart2=xmlPart2+updXML.toString();
										//Document cpmplteDoc=readXMLAsDocument(xmlPart1+xmlPart2+xmlPart3);
									}
								}
							}
						}
					}
				}
			}
		}while(sourceXML.contains("2000C>")) ;
		if(isLastTestPassed()) {
			Document doc271=readXMLAsDocument(xmlPart1+xmlPart2+xmlPart3);
			updateXML(doc271, File271);
		}









//
//
//
//
////Document doc=SOAPServiceUtils.convertXMLStringtoDocument(xml);
//Document doc_271_file=readXMLAsDocument(targetXML);
//if(dataRules.get(i).get(2).trim().equalsIgnoreCase("270")) {
//	for(int j=0;j<childTags.length;j++) {
//		String child =getChildEleValue(doc_270_file,parentTags,childTags[j]);
//		System.out.println(child);
//
//		//Update XML nodevalue 
//		updateXMLNodeValue(doc_271_file,File271,parentTags,childTags[j],child);
//	}
//}else {
//	if(getCellValue("Segment").equalsIgnoreCase("AAA")) {
//		value=dataRules.get(i).get(4).toString();
//		for(int j=0;j<childTags.length;j++) {
//			//Update XML nodevalue 
//			updateXMLNodeValue(doc_271_file,File271,parentTags,childTags[j],value);
//		}
//	}
//}
//}
//}

//			System.out.println("Row: "+excelData.get(i));
//			for(int j=0;j<excelData.get(i).size();j++) {
//				if(dataRules.get(i).get(j).equalsIgnoreCase("Member Not Found")) {
//					dataRules.get(index)
//				}
//				System.out.println(excelData.get(i).get(j));
//			}
//	}

//
//		try {
//			logExtentReport("Generate 271 File " + getCellValue("TCName"));
//			
//			// Read XML from file into a string
//						xml = readXMLFrmFile(File270);
//
//						// update 271 Template XML request with Data
//						
//						
//						
//						Document doc=SOAPServiceUtils.convertXMLStringtoDocument(xml);
//						
//						
//						// get child elements 
//						NodeList nodeList = doc.getElementsByTagName("*");
//						for (int i = 0; i < nodeList.getLength(); i++) {
//							Element element = (Element) nodeList.item(i);
//							if (element.getNodeName().equalsIgnoreCase("v22:GenderCode")) {
//								NodeList genderCodeChilds = nodeList.item(i).getChildNodes();
//								for (int k = 0; k < genderCodeChilds.getLength(); k++) {
//									Node child = genderCodeChilds.item(k);
//									if (child.getNodeName().equalsIgnoreCase("v22:name")) {
//										child.setTextContent(getCellValue("Gender"));
//										// Update XML as per modifications
//										updateXML(doc, srcFile);
//										break;
//									}
//								}
//							}
//						}
//			
//			}catch (Exception e) {
//				// TODO: handle exception
//			}
}



}