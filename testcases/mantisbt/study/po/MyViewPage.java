package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyViewPage extends MantisBTPage{
	@FindBy(linkText="Manage")
	private WebElement manageLink;
	@FindBy(linkText="Report Issue")
	private WebElement reportIssueLink;
	@FindBy(linkText="View Issues")
	private WebElement viewIssueLink;
	@FindBy(linkText="Summary")
	private WebElement summaryLink;
	private WebDriver driver;
	
	public MyViewPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageUserPage goToManageUserPage(){
		manageLink.click();
		ManageUserPage page = new ManageUserPage(driver);
		return page;
	}
	
	public LoginSelectProjectPage goToLoginSelectProjectPage(){
		reportIssueLink.click();
		LoginSelectProjectPage page = new LoginSelectProjectPage(driver);
		return page;
	}
	
	public ViewAllBugPage goToViewAllBugPage(){
		viewIssueLink.click();
		ViewAllBugPage page = new ViewAllBugPage(driver);
		return page;
	}
	
	public SummaryPage goToSummaryPage(){
		summaryLink.click();
		SummaryPage page = new SummaryPage(driver);
		return page;
	}
	
	public LoginPage doLogout(WebDriver driver){
		super.doLogout();
		LoginPage page = new LoginPage(driver);
		return page;
	}
}
