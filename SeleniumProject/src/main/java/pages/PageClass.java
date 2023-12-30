package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.SuperHelper;

public class PageClass extends SuperHelper {
public  static WebDriver driver=null;
	public static PageClass thisObj;
	
	
	
	public static PageClass get(WebDriver driver1){
		thisObj=PageFactory.initElements(driver, PageClass.class);
		return thisObj;
	}
	
	@FindBy(how=How.NAME,using="q")
	public WebElement googleSearch;
	
	@FindBy(how=How.XPATH,using="//input[@name='btnK']")
	public WebElement searchButton;
	
	
	
	
	
}
