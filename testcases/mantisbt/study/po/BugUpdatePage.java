package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BugUpdatePage {
	private WebDriver driver;
	@FindBy(name="severity")
	private WebElement severityList;
	@FindBy(name="status")
	private WebElement statusList;
	@FindBy(name="priority")
	private WebElement priorityList;
	@FindBy(css="input.button")
	private WebElement updateButton;
	@FindBy(name="summary")
	private WebElement summary;

	public BugUpdatePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public BugUpdateProceedPage updateStatus(String status){
		new Select(statusList).selectByVisibleText(status);
		updateButton.click();
		BugUpdateProceedPage page = new BugUpdateProceedPage(driver);
		return page;
	}
	
	public BugUpdateProceedPage updateSeverity(String severity){
		new Select(severityList).selectByVisibleText(severity);
		updateButton.click();
		BugUpdateProceedPage page = new BugUpdateProceedPage(driver);
		return page;
	}
	
	public BugUpdateProceedPage updatePriority(String priority){
		new Select(priorityList).selectByVisibleText(priority);
		updateButton.click();
		BugUpdateProceedPage page = new BugUpdateProceedPage(driver);
		return page;
	}
	
	public BugUpdateProceedPage updateSummary(String summ){
		summary.clear();
		summary.sendKeys(summ);
		updateButton.click();
		BugUpdateProceedPage page = new BugUpdateProceedPage(driver);
		return page;
	}
}
