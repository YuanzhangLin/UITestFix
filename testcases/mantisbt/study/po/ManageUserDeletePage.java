package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUserDeletePage {
	private WebDriver driver;
	@FindBy(css="input.button")
	private WebElement confirm;
	@FindBy(linkText="Click here to proceed")
	private WebElement proceed;
	
	public ManageUserDeletePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageUserPage delete(){
		confirm.click();
		proceed.click();
		ManageUserPage page = new ManageUserPage(driver);
		return page;
	}
	
}
