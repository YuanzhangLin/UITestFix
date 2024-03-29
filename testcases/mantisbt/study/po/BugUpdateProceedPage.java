package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugUpdateProceedPage {
	private WebDriver driver;
	@FindBy(linkText="Click here to proceed")
	private WebElement proceedLink;

	public BugUpdateProceedPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ViewPage proceed(){
		proceedLink.click();
		ViewPage page = new ViewPage(driver);
		return page;
	}
}
