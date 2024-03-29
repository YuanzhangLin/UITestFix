package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageProjectCategoryDeletePage {
	private WebDriver driver;
	@FindBy(css="input.button")
	private WebElement deleteButton;
	@FindBy(linkText="Click here to proceed")
	private WebElement proceed;
	
	public ManageProjectCategoryDeletePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectEditPage delete(){
		deleteButton.click();
		proceed.click();
		ManageProjectEditPage page = new ManageProjectEditPage(driver);
		return page;
	}
	
	
}
