package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
	private WebDriver driver;
	@FindBy(xpath=".//*[@id='claroBody']/p[1]/span/a")
	private WebElement statistics;
	
	
	public ProfilePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public TrackUserReportPage viewStats() throws InterruptedException{
		Thread.sleep(2000);
		statistics.click();
		TrackUserReportPage page = new TrackUserReportPage(driver);
		return page;
	}
}
