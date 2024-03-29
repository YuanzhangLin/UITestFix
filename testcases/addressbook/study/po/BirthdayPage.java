package testcases.addressbook.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BirthdayPage {
	private WebDriver driver;
	
	public BirthdayPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getDay(int i){
		WebElement day = driver.findElement(By.xpath(".//*[@id='birthdays']/tbody/tr["+(i+1)+"]/td[1]"));
		return day.getText();
	}
	
	public String getMonth(){
		WebElement month = driver.findElement(By.xpath(".//*[@id='birthdays']/tbody/tr[1]/th"));
		return month.getText();
	}
	
	public String getLastName(int i){
		WebElement last = driver.findElement(By.xpath(".//*[@id='birthdays']/tbody/tr["+(i+1)+"]/td[2]"));
		return last.getText();
	}
	
	public String getFirstName(int i){
		WebElement first = driver.findElement(By.xpath(".//*[@id='birthdays']/tbody/tr["+(i+1)+"]/td[3]"));
		return first.getText();
	}
	
	public String getEmail(int i){
		WebElement mail = driver.findElement(By.xpath(".//*[@id='birthdays']/tbody/tr["+(i+1)+"]/td[4]"));
		return mail.getText();
	}
	
	public String getPhone(int i){
		WebElement mail = driver.findElement(By.xpath(".//*[@id='birthdays']/tbody/tr["+(i+1)+"]/td[5]"));
		return mail.getText();
	}
	
}
