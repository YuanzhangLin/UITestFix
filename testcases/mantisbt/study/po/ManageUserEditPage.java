package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUserEditPage extends MantisBTPage{
	private WebDriver driver;
	@FindBy(partialLinkText="Manage Users")
	private WebElement manageUserLink;
	@FindBy(xpath="//input[@value='Delete User']")
	private WebElement deleteButton;
	@FindBy(css="input.button")
	private WebElement updateButton;
	@FindBy(name="realname")
	private WebElement realname;	
	@FindBy(name="username")
	private WebElement username;	
	
	public ManageUserEditPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageUserPage goToManageUserPage(){
		manageUserLink.click();
		ManageUserPage page = new ManageUserPage(driver);
		return page;		
	}
	
	public ManageUserDeletePage deleteUser(){
		deleteButton.click();
		ManageUserDeletePage page = new ManageUserDeletePage(driver);
		return page;
	}
	
	public ManageUserUpdateProceedPage updateRealname(String realname){
		this.realname.clear();
		this.realname.sendKeys(realname);
		updateButton.click();
		ManageUserUpdateProceedPage page = new ManageUserUpdateProceedPage(driver);
		return page;
	}
	
	public ManageUserUpdateProceedPage updateUsername(String username){
		this.username.clear();
		this.username.sendKeys(username);
		updateButton.click();
		ManageUserUpdateProceedPage page = new ManageUserUpdateProceedPage(driver);
		return page;
	}
	
	public String getRealname(){
		return realname.getAttribute("value");
	}
}
