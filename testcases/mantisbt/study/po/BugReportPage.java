package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BugReportPage extends MantisBTPage {
	private WebDriver driver;
	@FindBy(name="category")
	private WebElement category;
	@FindBy(name="reproducibility")
	private WebElement reproducibility;
	@FindBy(name="severity")
	private WebElement severity;
	@FindBy(name="priority")
	private WebElement priority;
	@FindBy(name="summary")
	private WebElement summary;
	@FindBy(name="description")
	private WebElement description;
	@FindBy(css="input.button")
	private WebElement confirm;
	@FindBy(xpath="html/body/div[3]/table/tbody/tr[2]/td/p")
	private WebElement errorMsg;
	
	public BugReportPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public BugReportProceedPage submitIssue(String category, String reproducibility, String severity, String priority, String summary, String description){
		new Select(this.category).selectByVisibleText(category);
		new Select(this.reproducibility).selectByVisibleText(reproducibility);
		new Select(this.priority).selectByVisibleText(priority);
		new Select(this.severity).selectByVisibleText(severity);
		this.summary.sendKeys(summary);
		this.description.sendKeys(description);
		confirm.click();
		BugReportProceedPage page = new BugReportProceedPage(driver);
		return page;		
	}
	
	public void submitEmptyIssue(){
		confirm.click();	
	}
	
	public String getErrorMsg(){
		return errorMsg.getText();
	}
	
	
}
