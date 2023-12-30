import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		File dir=new File("C:\\Geetha\\CSR");
		File[] files=dir.listFiles();
		int cal[]= {0,31,59,90,120,151,181,212,243,273,304,334};
	Map<String, String> map=new HashMap<String,String>();
	map.add(1,"Jan");
	
		z
		for(int i=0;i<files.length;i++) {
			String fileName=files[i].getName();
			String s=fileName.split("_")[1];
			s=fileName.substring(0, 2);
		
			
			SimpleDateFormat sdf=new SimpleDateFormat();
			sdf.format(date)
			Calendar cal=new Calendar
		}
		

	}

}
