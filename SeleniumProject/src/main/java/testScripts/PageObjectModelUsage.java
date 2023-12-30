package testScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.Home;

public class PageObjectModelUsage {
	
	static Home home;
	
	
	
	public static void main(String args[]) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "");
		driver=new ChromeDriver();
		home=new Home(driver);
		home.name.sendKeys("");
				
	}

}
