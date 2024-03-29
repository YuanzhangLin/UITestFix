package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageProjectCategoryUpdatePage extends MantisBTPage{
	private WebDriver driver;
	@FindBy(linkText="Click here to proceed")
	private WebElement proceedLink;
	@FindBy(xpath="html/body/div[3]/table/tbody/tr[2]/td/p")
	private WebElement errorMsg;
	
	public ManageProjectCategoryUpdatePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectEditPage proceed(){
		proceedLink.click();
		ManageProjectEditPage page = new ManageProjectEditPage(driver);
		return page;
	}
	
	public String getErrorMsg(){
		return errorMsg.getText();
	}
}
