package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageProjectDeletePage {
	private WebDriver driver;
	@FindBy(css="input.button")
	private WebElement deleteButton;
	
	public ManageProjectDeletePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectPage delete(){
		deleteButton.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
}
