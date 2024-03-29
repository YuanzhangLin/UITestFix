package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUserUpdateProceedPage extends MantisBTPage{
	private WebDriver driver;
	@FindBy(linkText="Click here to proceed")
	private WebElement proceedLink;
	@FindBy(xpath="html/body/div[3]/table/tbody/tr[2]/td/p")
	private WebElement errorMsg;
	
	public ManageUserUpdateProceedPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageUserEditPage proceed(){
		proceedLink.click();
		ManageUserEditPage page = new ManageUserEditPage(driver);
		return page;
	}
	
	public String getErrorMsg(){
		return errorMsg.getText();
	}
}
