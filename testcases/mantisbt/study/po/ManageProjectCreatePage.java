package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageProjectCreatePage extends MantisBTPage{
	private WebDriver driver;
	@FindBy(name="name")
	private WebElement name;
	@FindBy(name="status")
	private WebElement status;
	@FindBy(name="view_state")
	private WebElement view_state;
	@FindBy(name="description")
	private WebElement description;
	@FindBy(css="input.button")
	private WebElement confirm;
	@FindBy(xpath="html/body/div[3]/table/tbody/tr[2]/td/p")
	private WebElement errorMsg;
	
	public ManageProjectCreatePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectCreateProceedPage addNewProject(String name, int status, int viewstate, String descr){
		this.name.sendKeys(name);
		new Select(this.status).selectByIndex(status);
		new Select(this.view_state).selectByIndex(viewstate);
		this.description.sendKeys(descr);
		confirm.click();
		ManageProjectCreateProceedPage page = new ManageProjectCreateProceedPage(driver);
		return page;		
	}
	
	public ManageProjectCreateErrorPage addNewWrongProject(String name, int status, int viewstate, String descr){
		this.name.sendKeys(name);
		new Select(this.status).selectByIndex(status);
		new Select(this.view_state).selectByIndex(viewstate);
		this.description.sendKeys(descr);
		confirm.click();
		ManageProjectCreateErrorPage page = new ManageProjectCreateErrorPage(driver);
		return page;		
	}
	
	public void addNewEmptyProject(){
		confirm.click();	
	}
	
	public String getErrorMsg(){
		return errorMsg.getText();
	}
}
