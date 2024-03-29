package testcases.mantisbt.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUserPage extends MantisBTPage {
	private WebDriver driver;
	@FindBy(xpath="//input[@value='Create New Account']")
	private WebElement createNewAccount;
	@FindBy(xpath="(//a[contains(text(),'username001')])[3]")
	private WebElement username;
	@FindBy(xpath="html/body/table[5]/tbody/tr[4]/td[2]")
	private WebElement realname;
	@FindBy(xpath="html/body/table[5]/tbody/tr[4]/td[3]")
	private WebElement email;
	@FindBy(xpath="html/body/table[5]/tbody/tr[4]/td[4]")
	private WebElement access_lvl;
	@FindBy(xpath="html/body/table[3]/tbody/tr[1]/td[1]")
	private WebElement accountList;
	@FindBy(linkText="Manage Projects")
	private WebElement manageProjectsLink;
		
	public ManageUserPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageUserCreatePage createNewUser(){
		createNewAccount.click();
		ManageUserCreatePage page = new ManageUserCreatePage(driver);
		return page;
	}
	
	public String getUsername(){
		return username.getText();
	}
	
	public String getRealname(){
		return realname.getText();
	}
	
	public String getEmail(){
		return email.getText();
	}
	
	public String getAccessLvl(){
		return access_lvl.getText();
	}
	
	public String getUsernameI(int i){
		WebElement user = driver.findElement(By.xpath("html/body/table[5]/tbody/tr["+(i+3)+"]/td[1]/a"));
		return user.getText();
	}
	
	public String getAccessLvlI(int i){
		WebElement access = driver.findElement(By.xpath("html/body/table[5]/tbody/tr["+(i+3)+"]/td[4]"));
		return access.getText();
	}

	public ManageUserEditPage goToManageUserEditPage(){
		username.click();
		ManageUserEditPage page = new ManageUserEditPage(driver);
		return page;
	}
	
	public ManageUserEditPage goToManageUserEditPageI(){
		WebElement user = driver.findElement(By.xpath("html/body/table[5]/tbody/tr[4]/td[1]/a"));
		user.click();
		ManageUserEditPage page = new ManageUserEditPage(driver);
		return page;
	}
	
	public String getAccountListI(){
		WebElement accList = driver.findElement(By.xpath("html/body/table[3]/tbody/tr[1]/td[1]"));
		return accList.getText();
	}
	
	public String getAccountList(){
		return accountList.getText();
	}
	
	public ManageProjectPage goToManageProjectPage(){
		manageProjectsLink.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
}
