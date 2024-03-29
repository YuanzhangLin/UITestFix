package testcases.mantisbt.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageProjectPage extends MantisBTPage{
	private WebDriver driver; 
	@FindBy(css="td.form-title > form > input.button-small")
	private WebElement createNewProjectButton;
	@FindBy(linkText="Project001")
	private WebElement name;
	@FindBy(xpath="html/body/table[3]/tbody/tr[3]/td[2]")
	private WebElement status;
	@FindBy(xpath="html/body/table[3]/tbody/tr[3]/td[4]")
	private WebElement viewstate;
	@FindBy(xpath="html/body/table[3]/tbody/tr[3]/td[5]")
	private WebElement description;
	
	public ManageProjectPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectCreatePage createNewProject(){
		createNewProjectButton.click();
		ManageProjectCreatePage page = new ManageProjectCreatePage(driver);
		return page;
	}
	
	public String getProjectName(){
		return name.getText();
	}
	
	public String getProjectStatus(){
		return status.getText();
	}
	
	public String getProjectViewState(){
		return viewstate.getText();
	}
	
	public String getProjectDescription(){
		return description.getText();
	}
	
	public ManageProjectEditPage goToManageProjectEditPage(){
		name.click();
		ManageProjectEditPage page = new ManageProjectEditPage(driver);
		return page;
	}
	
	public ManageProjectEditPage goToManageProjectEditPageI(){
		WebElement proj = driver.findElement(By.linkText("Project1"));
		proj.click();
		ManageProjectEditPage page = new ManageProjectEditPage(driver);
		return page;
	}
	
	public String getProjectNameI(int i){
		WebElement sub = driver.findElement(By.xpath("html/body/table[3]/tbody/tr["+(i+3)+"]/td[1]/a"));
		return sub.getText();
	}
	
	public ManageProjectEditPage goToManageSubProjectEditPageI(int i){
		WebElement proj = driver.findElement(By.partialLinkText("sub"+i));
		proj.click();
		ManageProjectEditPage page = new ManageProjectEditPage(driver);
		return page;
	}
	
	public boolean isSubProjectPresent(int i){
		try{
			WebElement sub = driver.findElement(By.xpath("html/body/table[3]/tbody/tr["+(i+3)+"]/td[1]/a"));
		}
		catch(NoSuchElementException e){
			return false;
		}
		return true;
	}
}
