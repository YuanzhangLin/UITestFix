package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthCoursesPage extends ClarolinePage{
	private WebDriver driver;
	@FindBy(id="keyword")
	private WebElement searchBox;
	@FindBy(css="input[type='submit']")
	private WebElement searchButton;
	@FindBy(css="img[alt='Enrolment']")
	private WebElement enrolButton;
	@FindBy(css="img[alt='Unsubscribe']")
	private WebElement removeEnrolButton;
	@FindBy(css="img[alt='Locked']")
	private WebElement lockedButton;
	@FindBy(name="registrationKey")
	private WebElement coursePsw;
	@FindBy(css="input[type='submit']")
	private WebElement submitPsw;
	
	public AuthCoursesPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchCourse(String course) throws InterruptedException{
		Thread.sleep(2000);
		searchBox.sendKeys(course);
		searchButton.click();
		Thread.sleep(3000);
	}
	
	public void enrol() throws InterruptedException{
		enrolButton.click();
		Thread.sleep(1000);
	}
	
	public void enrolPassword(String password) throws InterruptedException{
		enrolButton.click();
		Thread.sleep(2000);
		coursePsw.sendKeys(password);
		submitPsw.click();
		Thread.sleep(2000);
	}
	
	public void enrolDenied() throws InterruptedException{
		lockedButton.click();
		Thread.sleep(2000);
	}
	
	public void removeEnrol() throws InterruptedException{
		removeEnrolButton.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
	}
}
