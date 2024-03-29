package testcases.collabtive.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageMilestonePage extends CollabtivePage{
	private WebDriver driver;
	@FindBy(id="add_butn")
	private WebElement addButton;
	@FindBy(id="name")
	private WebElement name;
	@FindBy(id="tinymce")
	private WebElement descr;
	@FindBy(css="button[type='submit']")
	private WebElement confirm;
	@FindBy(linkText="Milestone001")
	private WebElement milestone;
	@FindBy(css="a.tool_del")
	private WebElement removeButton;
	@FindBy(css="a.butn_check")
	private WebElement closeButton;
	@FindBy(css="a.butn_checked")
	private WebElement openButton;
	@FindBy(id="donebutn")
	private WebElement showClosed;
	@FindBy(css="a.tool_edit")
	private WebElement editMilestoneButton;
	@FindBy(id="end")
	private WebElement milestoneDate;
	@FindBy(css="tr.marker-late > td.tools > a.tool_del")
	private WebElement removeLateMilestoneButton;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]/td[2]/div/a")
	private WebElement milestoneName;
	
	public ManageMilestonePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void addMilestone(String namem, String description) throws InterruptedException{
		addButton.click();
		Thread.sleep(2000);
		name.sendKeys(namem);
		driver.switchTo().frame("desc_ifr");
		descr.sendKeys(description);
		driver.switchTo().defaultContent();
		confirm.click();
		Thread.sleep(2000);
	}
	
	public void addLateMilestone(String namem, String date) throws InterruptedException{
		addButton.click();
		Thread.sleep(2000);
		name.sendKeys(namem);
		milestoneDate.clear();
		milestoneDate.sendKeys(date);
		driver.findElement(By.xpath("//*[@id=\"datepicker_miles\"]/table/tbody/tr[8]/td/a")).click();
		confirm.click();
		Thread.sleep(2000);
	}
	
	public String getMilestoneName(){
		return milestoneName.getText();
	}
	
	public String getMilestoneDate(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]/td[3]")).getText();
	}
	
	public String getLateMilestoneName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[2]/table/tbody/tr[1]/td[2]/div/a")).getText();
	}
	
	public String getLateMilestoneDate(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[2]/table/tbody/tr[1]/td[3]")).getText();
	}
	
	public void getMilestoneDetails() throws InterruptedException{
		milestone.click();
		Thread.sleep(1000);
	}
	
	public String getMilestoneDescription(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[5]/div")).getText();
	}
	
	public Alert removeMilestone() throws InterruptedException{
		removeButton.click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public Alert removeLateMilestone() throws InterruptedException{
		removeLateMilestoneButton.click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public void closeMilestone() throws InterruptedException{
		closeButton.click();
		Thread.sleep(3000);
	}
	
	public void showClosedMilestone() throws InterruptedException{
		showClosed.click();
		Thread.sleep(2000);
	}
	
	public String getClosedMilestoneName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/div/a")).getText();
	}
	
	public String getReverseClosedMilestoneTitle(){
		WebElement checkButton = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/a"));
		return checkButton.getAttribute("title");
	}														 
	
	public void openMilestone() throws InterruptedException{
		openButton.click();
		Thread.sleep(2000);
	}
	
	public String getReverseOpenedMilestoneTitle(){
		WebElement checkButton = driver.findElement(By.xpath(" html/body/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]/td[1]/a"));
		return checkButton.getAttribute("title");
	}
	
	public String getOpenedMilestoneName(){
		return milestoneName.getText();
	}										
	
	public String getTasklistName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[5]/div[3]/ul/li/div/table/tbody/tr[2]/td/span/a")).getText();
	}
	
	public void editMilestoneDate(String date) throws InterruptedException{
		editMilestoneButton.click();
		Thread.sleep(2000);
		milestoneDate.clear();
		milestoneDate.sendKeys(date);
		driver.findElement(By.xpath("//*[@id=\"datepicker_mile\"]/table/tbody/tr[8]/td/a")).click();
		confirm.click();
	}

}
