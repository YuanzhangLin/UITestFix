package testcases.mrbs.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends MRBSPage{
	private WebDriver driver;
	@FindBy(xpath="html/body/b[1]")
	private WebElement msg;
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getErrorMsg(){
		return msg.getText();
	}
	
	public String getEntryName(int i){
		WebElement name = driver.findElement(By.xpath("html/body/table[2]/tbody/tr["+(i+1)+"]/td[3]"));
		return name.getText();
	}
	
	public String getEntryDescription(int i){
		WebElement desc = driver.findElement(By.xpath("html/body/table[2]/tbody/tr["+(i+1)+"]/td[4]"));
		return desc.getText();
	}
}
