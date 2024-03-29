package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugReportProceedPage {
	private WebDriver driver;
	@FindBy(xpath="html/body/div[3]/span[2]/a")
	private WebElement proceedLink;
	
	public BugReportProceedPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ViewAllBugPage proceed(){
		proceedLink.click();
		ViewAllBugPage page = new ViewAllBugPage(driver);
		return page;
	}
}
