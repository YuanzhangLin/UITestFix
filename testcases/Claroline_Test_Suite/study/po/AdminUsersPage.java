package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminUsersPage extends ClarolinePage{
	private WebDriver driver;
	@FindBy(xpath=".//*[@id='L0']")
	private WebElement lastName;
	@FindBy(xpath=".//*[@id='claroBody']/table[2]/tbody/tr/td[3]")
	private WebElement firstName;
	@FindBy(xpath=".//*[@id='claroBody']/table[2]/tbody/tr/td[6]")
	private WebElement role;
	@FindBy(css="img[alt='Delete']")
	private WebElement removeUserButton;
	
	public AdminUsersPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getLastName(){
		return lastName.getText();
	}
	
	public String getFirstName(){
		return firstName.getText();
	}
	
	public String getRole(){
		return role.getText();
	}
	
	public void removeUser() throws InterruptedException{
		removeUserButton.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
	}
	
	public String getLastNameI(int i){
		WebElement last = driver.findElement(By.xpath(".//*[@id='L"+i+"']"));
		return last.getText();
	}
	
	public boolean isLastNamePresent(){
		try{
			driver.findElement(By.xpath(".//*[@id='L0']"));
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}
	
	
}
