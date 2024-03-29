package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewPage {
	private WebDriver driver;
	@FindBy(linkText="View Issues")
	private WebElement viewIssueLink;

	public ViewPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ViewAllBugPage goToViewAllBugPage(){
		viewIssueLink.click();
		ViewAllBugPage page = new ViewAllBugPage(driver);
		return page;
	}
	
}
