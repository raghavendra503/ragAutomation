package testScripts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateExamples {
public static void main(String[] args) throws ParseException {
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("MMMM dd yy");
	System.out.println(sdf.format(date));
	//date=sdf.parse("January 16 20");
	Calendar cal=Calendar.getInstance();
	cal.setTime(date);
	System.out.println(sdf.format(cal.getTime()));
	
	//Convert one format to another
	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MMMM-dd");
	SimpleDateFormat sdf2=new SimpleDateFormat("dd-MMM-yy");
	Date date1=sdf1.parse("2011-Jan-23");
	System.out.println(sdf2.format(date1));
	
	//Convert string o date
	Date date2=sdf.parse("11-11-2011");
	
	
}
}
