package testcases.mrbs.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MRBSPage {
	@FindBy(css="input[type='submit']")
	private WebElement logoutButton;
	@FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr/td[7]/form/input[3]")
	private WebElement loginButton;
	@FindBy(name="search_str")
	private WebElement searchBox;
	@FindBy(linkText="Meeting Room Booking System")
	private WebElement goToDayPageLink;
	
	public AdminPage doLogin(WebDriver driver){
		loginButton.click();
		AdminPage page = new AdminPage(driver);
		return page;
	}
	
	public void doLogout(){
		logoutButton.click();
	}
	
	public String getBodyText(WebDriver driver){
		WebElement body = driver.findElement(By.tagName("body"));
		return body.getText();
	}
	
	public SearchPage searchEntry(WebDriver driver, String name){
		searchBox.sendKeys(name);
		searchBox.submit();
		SearchPage page = new SearchPage(driver);
		return page;
	}
	
	public DayPage goToDayPage(WebDriver driver){
		goToDayPageLink.click();
		DayPage page = new DayPage(driver);
		return page;
	}
	
	
}
