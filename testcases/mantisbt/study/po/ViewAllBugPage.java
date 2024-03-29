package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ViewAllBugPage extends MantisBTPage{
	private WebDriver driver;
	@FindBy(xpath=".//*[@id='buglist']/tbody/tr[4]/td[6]")
	private WebElement category;
	@FindBy(xpath=".//*[@id='buglist']/tbody/tr[4]/td[7]")
	private WebElement severity;
	@FindBy(xpath=".//*[@id='buglist']/tbody/tr[4]/td[10]")
	private WebElement summary;
	@FindBy(xpath=".//*[@id='buglist']/tbody/tr[4]/td[3]/img")
	private WebElement priority;
	@FindBy(name="bug_arr[]")
	private WebElement selectIssueCheckBox;
	@FindBy(name="action")
	private WebElement actionList;
	@FindBy(css="input.button")
	private WebElement confirmButton;
	@FindBy(xpath=".//*[@id='buglist']/tbody/tr[4]/td[8]")
	private WebElement status;
	@FindBy(css="img[alt='Update Issue']")
	private WebElement updateButton;

	public ViewAllBugPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getCategory(){
		return category.getText();
	}
	
	public String getSeverity(){
		return severity.getText();
	}
	
	public String getPriority(){
		return priority.getAttribute("title");
	}
	
	public String getSummary(){
		return summary.getText();
	}
	
	public void selectIssue(){
		selectIssueCheckBox.click();
	}
	
	public void selectDelete(){
		new Select(actionList).selectByVisibleText("Delete");
	}
	
	public void selectAssign(){
		new Select(actionList).selectByVisibleText("Assign");
	}
	
	public void confirmAction(){
		confirmButton.click();
	}
	
	public BugActionGroupPage delete(){
		selectIssue();
		selectDelete();
		confirmAction();
		BugActionGroupPage page = new BugActionGroupPage(driver);
		return page;
	}
	
	public BugActionGroupPage assign(){
		selectIssue();
		selectAssign();
		confirmAction();
		BugActionGroupPage page = new BugActionGroupPage(driver);
		return page;
	}
	
	public String getStatus(){
		return status.getText();
	}
	
	public BugUpdatePage update(){
		updateButton.click();
		BugUpdatePage page = new BugUpdatePage(driver);
		return page;		
	}
}
