package testcases.mrbs.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DayPage extends MRBSPage{
	private WebDriver driver;
	@FindBy(linkText="Admin")
	private WebElement adminLink;	
	@FindBy(name="day")
	private WebElement day;
	@FindBy(name="month")
	private WebElement month;
	@FindBy(name="year")
	private WebElement year;
	@FindBy(css="input[type='SUBMIT']")
	private WebElement submit;
	@FindBy(xpath="html/body/h2")
	private WebElement date;
	@FindBy(linkText="Go To Day After>>")
	private WebElement nextDay;
	
	public DayPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AdminPage goToAdminPage(){
		adminLink.click();
		AdminPage page = new AdminPage(driver);
		return page;
	}	
	
	public void selectBuilding(String name) throws InterruptedException{
		WebElement myBuildingLink = driver.findElement(By.linkText(name));
		myBuildingLink.click();
		Thread.sleep(2000);
	}
	
	public String getRoomName(int i){
		WebElement roomName = driver.findElement(By.xpath("/html/body/table[5]/tbody/tr[1]/th["+(i+1)+"]/a"));
		return roomName.getText();
	}
	
	public EditEntryPage addEntry(int row, int col){
		WebElement add = driver.findElement(By.xpath("html/body/table[5]/tbody/tr["+(row+1)+"]/td["+(col+1)+"]/table/tbody/tr/td/center/a/img"));
		add.click();
		EditEntryPage page = new EditEntryPage(driver);
		return page;
	}
	
	public String getEntryName(int i,int j ){
		WebElement entry = driver.findElement(By.xpath("/html/body/table[5]/tbody/tr["+(i+1)+"]/td["+(j+1)+"]/a"));
		return entry.getText();
	}

	public void selectDay(String dd, String mm, String yy) throws InterruptedException{
		new Select(day).selectByVisibleText(dd);
		new Select(month).selectByVisibleText(mm);
		new Select(year).selectByVisibleText(yy);
		submit.click();
		Thread.sleep(2000);
	}
	
	public String getDate(){
		return date.getText();
	}
	
	public ViewEntryPage selectEntry(String name){
		WebElement entry = driver.findElement(By.linkText(name));
		entry.click();
		ViewEntryPage page = new ViewEntryPage(driver);
		return page;
	}
	
	public void goToNextDay() throws InterruptedException{
		nextDay.click();
		Thread.sleep(2000);
	}
	
	
}
