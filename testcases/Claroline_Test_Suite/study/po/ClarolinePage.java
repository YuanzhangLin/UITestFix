package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClarolinePage {
	@FindBy(linkText="Logout")
	private WebElement logout;
	@FindBy(xpath=".//*[@id='claroBody']/div[1]/div[1]")
	private WebElement message;
	
	public void doLogout(){
		logout.click();
	}
	
	public String getBodyText(WebDriver driver){
		String bodyText = driver.findElement(By.tagName("body")).getText();
		return bodyText;
	}
	
	public String getMessage(){
		return message.getText();
	}
	
}
