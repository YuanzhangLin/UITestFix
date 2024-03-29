package testcases.collabtive.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CollabtivePage {
	private WebDriver driver;
	@FindBy(xpath=".//*[@id='mainmenue']/li[4]/a")
	private WebElement logout;
	@FindBy(xpath=".//*[@id='mainmenue']/li[3]/a")
	private WebElement simpleLogout;
	@FindBy(id="query")
	private WebElement searchBox;
	@FindBy(css="fieldset > button[type='submit']")
	private WebElement searchButton;
	
	public void doLogout(){
		logout.click();
	}
	
	public void doSimpleLogout(){
		simpleLogout.click();
	}
	
	public String getBodyText(WebDriver driver){
		return driver.findElement(By.tagName("body")).getText();
	}
	
	public ManageSearchPage searchProject(String projectName, WebDriver driver) throws InterruptedException{
		searchBox.sendKeys(projectName);
		searchButton.click();
		Thread.sleep(5000);
		ManageSearchPage page = new ManageSearchPage(driver);
		return page;
	}
	
}
