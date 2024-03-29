package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedUserSearchPage {
	private WebDriver driver;
	@FindBy(id="lastName")
	private WebElement lastname;
	@FindBy(id="action")
	private WebElement rolename;
	@FindBy(css="input.claroButton")
	private WebElement searchButton;
		
	public AdvancedUserSearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AdminUsersPage search(String name, String role) throws InterruptedException{
		lastname.sendKeys(name);
		new Select(rolename).selectByVisibleText(role);
		searchButton.click();
		Thread.sleep(3000);
		AdminUsersPage page = new AdminUsersPage(driver);
		return page;
	}
}
