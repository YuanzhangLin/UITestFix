package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageProjectCreateProceedPage {
	private WebDriver driver;
	@FindBy(linkText="Click here to proceed")
	private WebElement proceedLink;
	
	public ManageProjectCreateProceedPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectPage proceed(){
		proceedLink.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	
}
