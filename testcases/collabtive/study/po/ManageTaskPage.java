package testcases.collabtive.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageTaskPage extends CollabtivePage {
	private WebDriver driver; 
	@FindBy(id="addtasklists")
	private WebElement addTaskListButton;
	@FindBy(xpath=".//*[@id='content-left-in']/div/div[4]/div/a[3]")
	private WebElement deleteTaskListButton;
	@FindBy(id="name")
	private WebElement name;
	@FindBy(id="milestone")
	private WebElement milestoneList;
	@FindBy(css="button[type='submit']")
	private WebElement confirm;
	@FindBy(xpath=".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")
	private WebElement manageMilestoneLink;
	@FindBy(xpath=".//*[@id='content-left-in']/div/div[4]/div/a[1]")
	private WebElement closeTaskListButton;
	@FindBy(css="a.butn_check")
	private WebElement openTaskListButton;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[1]")
	private WebElement addTaskButton;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")
	private WebElement confirmTask;
	@FindBy(id="title")
	private WebElement taskTitle;
	@FindBy(name="assigned[]")
	private WebElement taskAssignTo;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[2]")
	private WebElement showClosedTasksButton;
	@FindBy(xpath=".//*[@id='contentwrapper']/div[1]/ul/li[1]/a")
	private WebElement manageProjectLink;
	@FindBy(xpath=".//*[@id='mainmenue']/li[1]/a")
	private WebElement desktopLink;

	
	public ManageTaskPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void addTaskList(String namet, String miles) throws InterruptedException{
		addTaskListButton.click();
		Thread.sleep(2000);
		name.sendKeys(namet);
		new Select(milestoneList).selectByVisibleText(miles);
		confirm.click();
		Thread.sleep(2000);
	}
	
	public String getTasklistName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[4]/h2/a")).getText();
	}
	
	public ManageMilestonePage goToMilestonePage(){
		manageMilestoneLink.click();
		ManageMilestonePage page = new ManageMilestonePage(driver);
		return page;
	}
	
	public Alert deleteTaskList(){
		deleteTaskListButton.click();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public void closeTaskList() throws InterruptedException{
		closeTaskListButton.click();
		Thread.sleep(2000);
	}
	
	public String getClosedTaskListName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[5]/div/table/tbody/tr[1]/td[2]/div/a")).getText();
	}
	
	public String getReverseClosedTaskListTitle(){
		WebElement checkButton = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[5]/div/table/tbody/tr[1]/td[1]/a"));
		return checkButton.getAttribute("title");
	}
	
	public void openTaskList() throws InterruptedException{
		openTaskListButton.click();
		Thread.sleep(2000);
	}
	
	public void addTask(String titlet, String assign) throws InterruptedException{
		addTaskButton.click();
		Thread.sleep(2000);
		taskTitle.sendKeys(titlet);
		new Select(taskAssignTo).selectByVisibleText(assign);
		confirmTask.click();
		Thread.sleep(2000);		
	}
	
	public Alert removeTask(){
		WebElement remove = driver.findElement(By.cssSelector("a.tool_del"));
		remove.click();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public Alert removeTaskN(int n){
		WebElement removeButton = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody["+n+"]/tr[1]/td[5]/a[2]"));
		removeButton.click();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public void closeTaskN(int n) throws InterruptedException{
		WebElement closeButton = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody["+n+"]/tr[1]/td[1]/a"));
		closeButton.click();								  
		Thread.sleep(2000);
	}
	
	public void showClosedTasks() throws InterruptedException{
		showClosedTasksButton.click();
		Thread.sleep(2000);
	}
	
	public void openTaskN(int n) throws InterruptedException{
		WebElement openButton = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[5]/div[2]/div/div/table/tbody["+n+"]/tr[1]/td[1]/a"));
		openButton.click();
		Thread.sleep(2000);
	}
	
	public ManageProjectPage goToManageProjectPage(){
		manageProjectLink.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	public IndexPage goToIndexPage(){
		desktopLink.click();
		IndexPage page = new IndexPage(driver);
		return page;
	}
	
}
