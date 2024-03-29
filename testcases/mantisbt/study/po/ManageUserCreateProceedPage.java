package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUserCreateProceedPage {
	@FindBy(xpath="html/body/div[3]/span/a")
	private WebElement proceedLink;
	private WebDriver driver;
	
	public ManageUserCreateProceedPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageUserEditPage proceed(){
		proceedLink.click();
		ManageUserEditPage page = new ManageUserEditPage(driver);
		return page;
	}
	
}
