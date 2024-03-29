package testcases.addressbook.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewPage {
	private WebDriver driver;
	
	public ViewPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getAddressBookContent(int i){
		WebElement content = driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td["+i+"]"));
		return content.getText();
	}
}
