package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSelectProjectPage {
	private WebDriver driver;
	@FindBy(css="input.button")
	private WebElement selectProjectButton;
	
	public LoginSelectProjectPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public BugReportPage goToBugReportPage(){
		selectProjectButton.click();
		BugReportPage page = new BugReportPage(driver);
		return page;
	}
}
