package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	private WebDriver driver;
	@FindBy(id="login")
	private WebElement username;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(name="submitAuth")
	private WebElement confirm;
	
	public IndexPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public DesktopPage login(String user, String pass){
		username.sendKeys(user);
		password.sendKeys(pass);
		confirm.click();
		DesktopPage page = new DesktopPage(driver);
		return page;
	}
	
	
	
}
