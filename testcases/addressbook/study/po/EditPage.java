package testcases.addressbook.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditPage extends AddressBookPage{
	private WebDriver driver;
	@FindBy(name="firstname")
	private WebElement firstName;
	@FindBy(name="lastname")
	private WebElement lastName;
	@FindBy(name="address")
	private WebElement address;
	@FindBy(name="home")
	private WebElement home;
	@FindBy(name="email")
	private WebElement email;
	@FindBy(name="bday")
	private WebElement bday;
	@FindBy(name="bmonth")
	private WebElement bmonth;
	@FindBy(name="byear")
	private WebElement byear;
	@FindBy(name="submit")
	private WebElement confirm;	
	@FindBy(name="update")
	private WebElement update;
	@FindBy(xpath="(//input[@name='update'])[2]")
	private WebElement remove;
	@FindBy(linkText="add next")
	private WebElement addNextLink;	
	
	public EditPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addAddressBook(String first, String last, String add, String hm, String mail, String day, String month, String year) throws InterruptedException
	{
		firstName.sendKeys(first);
		lastName.sendKeys(last);
		address.sendKeys(add);
		home.sendKeys(hm);
		email.sendKeys(mail);
		new Select(bday).selectByVisibleText(day);
		new Select(bmonth).selectByVisibleText(month);
		byear.sendKeys(year);
		confirm.click();
		Thread.sleep(2000);
	}
	
	public DeletePage removeAddressBook(){
		remove.click();
		DeletePage page = new DeletePage(driver);
		return page;
	}
	
	public void addNext() throws InterruptedException{
		addNextLink.click();
		Thread.sleep(2000);
	}
	
	public void edit(String add, String phone, String mail) throws InterruptedException{
		address.clear();
		address.sendKeys(add);
		home.clear();
		home.sendKeys(phone);
		email.clear();
		email.sendKeys(mail);
		update.click();
		Thread.sleep(2000);
	}
	
	
	
}
