package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage extends ClarolinePage {
	private WebDriver driver;
	@FindBy(xpath="(//img[@alt='Unregister'])[2]")
	private WebElement unregisterButton;
	
	public UserPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void removeEnrol() throws InterruptedException{
		Thread.sleep(2000);
		unregisterButton.click();
		Alert al = driver.switchTo().alert();
		Thread.sleep(1000);
		al.accept();
		Thread.sleep(1000);
		driver.navigate().refresh();
	}
}
