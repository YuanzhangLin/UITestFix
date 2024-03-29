package testcases.collabtive.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManageUserPage extends CollabtivePage{
	private WebDriver driver;
	@FindBy(id="add_butn_member")
	private WebElement addUserToProjectLink;
	@FindBy(id="addtheuser")
	private WebElement userList;
	@FindBy(css="button[type='submit']")
	private WebElement confirm;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div/div/ul/li[2]/div/table/tbody/tr[2]/td/span/a")
	private WebElement addedUsername;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div/div/ul/li[1]/div/table/tbody/tr[1]/td[2]/a/img")
	private WebElement userIcon;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div/div/ul/li[2]/div/table/tbody/tr[1]/td[3]/div/a[1]")
	private WebElement removeButton;
	
	public ManageUserPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void addUserToProject(String username) throws InterruptedException{
		addUserToProjectLink.click();
		Thread.sleep(2000);
		new Select(userList).selectByVisibleText(username);
		confirm.click();
		Thread.sleep(2000);
	}
	
	public String getAddedUsername(){
		return addedUsername.getText();
	}
	
	public ManageProjectPage removeUserFromProject() throws InterruptedException{
		Actions a = new Actions(driver);
		a.moveToElement(userIcon).perform();
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(("a.del"))));
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div/div/ul/li[2]/div/table/tbody/tr[1]/td[2]/a/img")));
		actions.perform();

		removeButton.click();
		Thread.sleep(3000);
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;		
	}
	
}
