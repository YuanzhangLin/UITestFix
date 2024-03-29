package testcases.mrbs.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewEntryPage {
	private WebDriver driver;
	@FindBy(linkText="Delete Entry")
	private WebElement removeEntryLink;
	@FindBy(linkText="Delete Series")
	private WebElement removeSerialEntryLink;
	
	public ViewEntryPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public DayPage removeEntry() throws InterruptedException{
		removeEntryLink.click();
		Alert al = driver.switchTo().alert();
		Thread.sleep(1000);
		al.accept();
		Thread.sleep(1000);
		DayPage page = new DayPage(driver);
		return page;
	}
	
	public DayPage removeSerialEntry() throws InterruptedException{
		removeSerialEntryLink.click();
		Alert al = driver.switchTo().alert();
		Thread.sleep(1000);
		al.accept();
		Thread.sleep(1000);
		DayPage page = new DayPage(driver);
		return page;
	}
}
