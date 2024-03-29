package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUserCreateErrorPage extends MantisBTPage{
	private WebDriver driver;
	@FindBy(xpath="html/body/div[3]/table/tbody/tr[2]/td/p")
	private WebElement errorMsg;
	
	public ManageUserCreateErrorPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getErrorMsg(){
		return errorMsg.getText();
	}
	
	
	
}
