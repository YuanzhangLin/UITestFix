package testcases.collabtive.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage extends CollabtivePage{
	private WebDriver driver;
	@FindBy(id="username")
	private WebElement username;
	@FindBy(id="pass")
	private WebElement password;
	@FindBy(css="button.loginbutn")
	private WebElement confirm;
	@FindBy(xpath="html/body/div[2]/div[2]/div[3]/div[2]/div/ul/li/a")
	private WebElement onlineUser;
	@FindBy(xpath=".//*[@id='mainmenue']/li[3]/a")
	private WebElement administrationLink;
	
	public IndexPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void login(String user, String pass){
		username.sendKeys(user);
		password.sendKeys(pass);
		confirm.click();
	}
	
	public String getOnlineUser(){
		return onlineUser.getText();
	}
	
	public AdminPage goToAdminPage(){
		administrationLink.click();
		AdminPage page = new AdminPage(driver);
		return page;
	}
	
}
