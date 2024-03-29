package testcases.addressbook.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class IndexPage extends AddressBookPage{
	private WebDriver driver;
	@FindBy(name="searchstring")
	private WebElement searchBox;
	@FindBy(css="input[type='submit']")
	private WebElement searchButton;
	@FindBy(xpath=".//*[@id='content']/label/strong")
	private WebElement searchResult;
	@FindBy(css="img[alt='Modifica']")
	private WebElement editAddressBookButton;
	@FindBy(name="add")
	private WebElement assignGroupButton;
	@FindBy(name="remove")
	private WebElement unassignGroupButton;
	@FindBy(name="to_group")
	private WebElement groupList;
	@FindBy(name="group")
	private WebElement groupSelectedList;
	
	public IndexPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchAddressBookName(String value) throws InterruptedException{
		searchBox.clear();
		searchBox.sendKeys(value);
		searchButton.click();
		Thread.sleep(2000);
	}
	
	public void searchAddressBookEmail(String value) throws InterruptedException{
		searchBox.sendKeys(value);
		searchButton.click();
		Thread.sleep(2000);
	}
	
	public void searchAddressBookTelephone(String value) throws InterruptedException{
		searchBox.sendKeys(value);
		searchButton.click();
		Thread.sleep(2000);
	}
	
	public String getFirstName(int i){
		WebElement firstName = driver.findElement(By.xpath(".//*[@id='maintable']/tbody/tr["+(i+1)+"]/td[3]"));
		return firstName.getText();
	}
	
	public String getLastName(int i){
		WebElement lastName = driver.findElement(By.xpath(".//*[@id='maintable']/tbody/tr["+(i+1)+"]/td[2]"));
		return lastName.getText();
	}
	
	public String getEmail(int i){
		WebElement email = driver.findElement(By.xpath(".//*[@id='maintable']/tbody/tr["+(i+1)+"]/td[4]"));
		return email.getText();
	}
	
	public String getTelephone(int i){
		WebElement phone = driver.findElement(By.xpath(".//*[@id='maintable']/tbody/tr["+(i+1)+"]/td[5]"));
		return phone.getText();
	}
	
	public String getSearchResult(){
		return searchResult.getText();
	}
	
	public EditPage editAddressBook(){
		editAddressBookButton.click();
		EditPage page = new EditPage(driver);
		return page;
	}
	
	public EditPage editAddressBookI(int i){
		WebElement edit = driver.findElement(By.xpath(".//*[@id='maintable']/tbody/tr["+(i+1)+"]/td[7]/a/img"));
		edit.click();
		EditPage page = new EditPage(driver);
		return page;
	}
	
	public GroupPage assignGroup(int i, String group) throws InterruptedException{
		WebElement checkButton = driver.findElement(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr["+(i+1)+"]/td[1]/input"));
		checkButton.click();
		new Select(groupList).selectByVisibleText(group);
		assignGroupButton.click();
		Thread.sleep(2000);
		GroupPage page = new GroupPage(driver);
		return page;
	}
	
	public GroupPage unassignGroup(int i, String group) throws InterruptedException{
		new Select(groupSelectedList).selectByVisibleText(group);
		WebElement checkButton = driver.findElement(By.xpath("html/body/div[1]/div[4]/form[2]/table/tbody/tr["+(i+1)+"]/td[1]/input"));
		checkButton.click();
		unassignGroupButton.click();
		Thread.sleep(2000);
		GroupPage page = new GroupPage(driver);
		return page;
	}

	public void viewAddressBooksByGroup(String group) throws InterruptedException{
		new Select(groupSelectedList).selectByVisibleText(group);
		Thread.sleep(2000);
	}
	
}
