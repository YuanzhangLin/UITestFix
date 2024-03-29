package testcases.addressbook.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GroupPage extends AddressBookPage{
	private WebDriver driver;
	@FindBy(name="new")
	private WebElement newButton;
	@FindBy(name="group_name")
	private WebElement groupName;
	@FindBy(name="group_header")
	private WebElement header;
	@FindBy(name="group_footer")
	private WebElement footer;
	@FindBy(name="submit")
	private WebElement confirm;
	@FindBy(name="selected[]")
	private WebElement checkButton;
	@FindBy(name="delete")
	private WebElement deleteButton;
	@FindBy(name="edit")
	private WebElement editButton;
	@FindBy(name="update")
	private WebElement confirmEdit;
	@FindBy(linkText="group page")
	private WebElement back;
	@FindBy(xpath=".//*[@id='content']/form[2]")
	private WebElement groupContainer;
	
	public GroupPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addNewGroup(String name, String head, String foot) throws InterruptedException{
		newButton.click();
		Thread.sleep(1000);
		groupName.sendKeys(name);
		header.sendKeys(head);
		footer.sendKeys(foot);
		confirm.click();
		Thread.sleep(2000);
	}
	
	public void deleteGroup() throws InterruptedException{
		checkButton.click();
		deleteButton.click();
		Thread.sleep(2000);
	}
	
	public void editGroup(String name, String head, String foot) throws InterruptedException{
		checkButton.click();
		editButton.click();
		Thread.sleep(2000);
		groupName.clear();
		groupName.sendKeys(name);
		header.clear();
		header.sendKeys(head);
		footer.clear();
		footer.sendKeys(foot);
		confirmEdit.click();
		Thread.sleep(2000);
	}
	
	public void editGroupI(int i, String name, String head, String foot) throws InterruptedException{
		WebElement check = driver.findElement(By.xpath(".//*[@id='content']/form[2]/input["+i+"]"));
		check.click();
		editButton.click();
		Thread.sleep(2000);
		groupName.clear();
		groupName.sendKeys(name);
		header.clear();
		header.sendKeys(head);
		footer.clear();
		footer.sendKeys(foot);
		confirmEdit.click();
		Thread.sleep(2000);
	}
	
	public void deleteAllGroups(int n) throws InterruptedException{
		WebElement check;
		for(int i=1; i<=n; i++){
			check = driver.findElement(By.xpath(".//*[@id='content']/form[2]/input["+i+"]"));
			check.click();
		}
		deleteButton.click();
		Thread.sleep(2000);
	}
	
	public void goBack() throws InterruptedException{
		back.click();
		Thread.sleep(1000);
	}
	
	public String getGroupsName(){
		return groupContainer.getText();
	}
	
}
