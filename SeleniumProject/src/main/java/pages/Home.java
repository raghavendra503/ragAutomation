package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.SuperHelper;

public class Home extends SuperHelper{
	WebDriver driver;
	
	public Home(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, Home.class);
	}
		
	//Web Elements Creation
	@FindBy(how = How.ID,using = "")
	public WebElement search;
	
	@FindBy(how=How.NAME,using="")
	public WebElement name;
	

}
