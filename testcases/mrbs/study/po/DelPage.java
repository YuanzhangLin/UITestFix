package testcases.mrbs.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DelPage {
	private WebDriver driver;
	@FindBy(linkText="YES!")
	private WebElement yes;
	
	public DelPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AdminPage remove(){
		yes.click();
		AdminPage page = new AdminPage(driver);
		return page;
	}
	
}
