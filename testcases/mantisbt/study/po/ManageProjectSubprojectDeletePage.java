package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageProjectSubprojectDeletePage {
	private WebDriver driver;
	@FindBy(linkText="Click here to proceed")
	private WebElement proceedLink;

	public ManageProjectSubprojectDeletePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectEditPage proceed(){
		proceedLink.click();
		ManageProjectEditPage page = new ManageProjectEditPage(driver);
		return page;
	}
}
