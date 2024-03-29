package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvancedCourseSearchPage {
	private WebDriver driver;
	@FindBy(id="intitule")
	private WebElement title;
	@FindBy(id="subscription_allowed")
	private WebElement allowed;
	@FindBy(id="subscription_key")
	private WebElement password;
	@FindBy(id="subscription_denied")
	private WebElement denied;
	@FindBy(css="input.claroButton")
	private WebElement searchButton;
		
	public AdvancedCourseSearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AdminCoursesPage searchAllowed(String tit) throws InterruptedException{
		title.sendKeys(tit);
		allowed.click();
		searchButton.click();
		Thread.sleep(3000);
		AdminCoursesPage page = new AdminCoursesPage(driver);
		return page;
	}
	
	public AdminCoursesPage searchPassword(String tit) throws InterruptedException{
		title.sendKeys(tit);
		password.click();
		searchButton.click();
		Thread.sleep(3000);
		AdminCoursesPage page = new AdminCoursesPage(driver);
		return page;
	}
	
	public AdminCoursesPage searchDenied(String tit) throws InterruptedException{
		title.sendKeys(tit);
		denied.click();
		searchButton.click();
		Thread.sleep(3000);
		AdminCoursesPage page = new AdminCoursesPage(driver);
		return page;
	}
}
