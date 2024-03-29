package testcases.mantisbt.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MantisBTPage {
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	public void doLogout(){
		logout.click();
	}
	
	public String getBodyText(WebDriver driver){
		String bodyText = driver.findElement(By.tagName("body")).getText();
		return bodyText;
	}
	
	public String getUrl(WebDriver driver){
		return driver.getCurrentUrl();
	}

}
