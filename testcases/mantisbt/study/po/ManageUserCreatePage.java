package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageUserCreatePage {
	private WebDriver driver;
	@FindBy(name="username")
	private WebElement username;
	@FindBy(name="realname")
	private WebElement realname;
	@FindBy(name="email")
	private WebElement email;
	@FindBy(name="access_level")
	private WebElement access_lvl;
	@FindBy(css="input.button")
	private WebElement confirm;

	public ManageUserCreatePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageUserCreateProceedPage createUser(String usern, String realn, String mail, int accesslvl){
		username.sendKeys(usern);
		realname.sendKeys(realn);
		email.sendKeys(mail);
		new Select(access_lvl).selectByIndex(accesslvl);
		confirm.click();
		ManageUserCreateProceedPage page = new ManageUserCreateProceedPage(driver);
		return page;
	}
	
	public ManageUserCreateErrorPage createWrongUser(String usern, String realn, String mail, int accesslvl){
		username.sendKeys(usern);
		realname.sendKeys(realn);
		email.sendKeys(mail);
		new Select(access_lvl).selectByIndex(accesslvl);
		confirm.click();
		ManageUserCreateErrorPage page = new ManageUserCreateErrorPage(driver);
		return page;
	}
	
	public ManageUserCreateErrorPage createEmptyUser(){
		confirm.click();
		ManageUserCreateErrorPage page = new ManageUserCreateErrorPage(driver);
		return page;
	}
	
}
