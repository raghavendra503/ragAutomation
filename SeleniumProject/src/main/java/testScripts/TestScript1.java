package testScripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.PageClass;

public class TestScript1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.setProperty("webdriver.chrome.driver", "C:\\Mine\\Work documents\\chromedriver.exe");
//		WebDriver driver=new ChromeDriver();
//		PageClass.get(driver).googleSearch.sendKeys("Hi");
//		PageClass.get(driver).searchButton.click();
//		
//		driver.switchTo().frame(0);
//		driver.switchTo().window("");
//		
//		WebDriverWait wait=new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.alertIsPresent());
//		
//		driver.manage().timeouts();
//		
//		
//		
//		driver.manage().window().maximize();
//		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
		List<String> l1=new ArrayList<String>();
		l1.add("Apple");
		l1.add("Banana");
		l1.add("Papaya");
		l1.remove("Banana");
//		Iterator<String> i1=l1.iterator();
//		while(i1.hasNext()) {
//			System.out.println(i1.next());
//		}
//		
//	for(String s:l1) {	
//		System.out.println(s);
//	}
//		
//	}

	Map<Integer, String> map=new HashMap<Integer, String>();
	map.put(1, "Value1");
	map.put(2, "Value2");
	map.put(3, "value3");
	map.put(4, "value4");
	map.put(1, "value4");
	
for(Map.Entry<Integer, String> m:map.entrySet()) {
	System.out.println(m.getValue());
}
	
	





	}
}
