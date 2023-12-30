import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

public class Test2 {

	public static void main(String[] args) throws IOException {
		g();
	}

	public static void g() throws IOException {
		int[] cal;
		boolean leap=false ;
		String value=null;
		File dir=new File("C:\\Geetha\\GraceFO\\JPL");
		File[] files=dir.listFiles();
		File destFile;
		int getMonthNum=0;
		Map<String, String> map1=new HashMap<String,String>();
		map1.put("1","Jan");
		map1.put("2","Feb");
		map1.put("3","Mar");
		map1.put("4","Apr");
		map1.put("5","May");
		map1.put("6","Jun");
		map1.put("7","Jul");
		map1.put("8","Aug");
		map1.put("9","Sep");
		map1.put("10","Oct");
		map1.put("11","Nov");
		map1.put("12","Dec");


		for(int i=0;i<files.length;i++) {
			String fileName=files[i].getName();
			String s=fileName.split("_")[1];
			String year=s.substring(0, 4);
			
			if ((Integer.valueOf(year) % 400 == 0) || ((Integer.valueOf(year) % 4 == 0) && (Integer.valueOf(year) % 100 != 0))) {
				leap=true;
				 cal=new int[]{31,60,91,121,152,182,213,244,274,305,335,366};
			}else {
				 cal=new int[]{31,59,90,120,151,181,212,243,273,304,334,365};
			}
				System.out.println(year);
				int month=Integer.valueOf(s.substring(4, 7));
				for(int j=0;j<cal.length;j++) {
					if(month<=cal[j]) {
						value=map1.get(String.valueOf(j+1));
						if(j!=0) {
						getMonthNum=month-cal[j-1];
						}else {
							getMonthNum=month;
						}
						System.out.println(value);
						break;
					}
				}

				File destDir=new File("C:\\Geetha\\GraceFO\\JPLDest\\"+value);
				if(!destDir.exists()) {
					destDir.mkdir();
				}
				
				if((new File(destDir+"\\"+year+value+".tif").exists())&& getMonthNum<=15){
					destFile=new File(destDir+"\\"+year+value+"_1.tif");
				}else {
					destFile=new File(destDir+"\\"+year+value+".tif");
				}
				
				FileUtils.copyFile(files[i],destFile);


			}
		}

	}
