package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminCoursesPage extends ClarolinePage{
	private WebDriver driver;
	@FindBy(xpath=".//*[@id='claroBody']/table[2]/tbody/tr/td[2]")
	private WebElement courseName;
	@FindBy(xpath=".//*[@id='L0']")
	private WebElement courseCode;
	
	public AdminCoursesPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getCourseName(){
		return courseName.getText();
	}
	
	public String getCourseCode(){
		return courseCode.getText();
	}
		
	public void removeCourse(int i) throws InterruptedException{
		WebElement removeButton = driver.findElement(By.cssSelector("#Course00"+i+"__00"+i+" > img"));
		removeButton.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
	}
	
}
