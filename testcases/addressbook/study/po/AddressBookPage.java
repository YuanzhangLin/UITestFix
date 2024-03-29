package testcases.addressbook.study.po;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressBookPage {
	@FindBy(linkText="nuovi")
	private WebElement newLink;
	@FindBy(linkText="gruppi")
	private WebElement newGroupLink;
	@FindBy(linkText="casa")
	private WebElement homeLink;
	@FindBy(linkText="compleanni")
	private WebElement birthdayLink;
	@FindBy(linkText="rubrica")
	private WebElement addressBookLink;
	@FindBy(linkText="stampare tutti")
	private WebElement printLink;
	@FindBy(xpath=".//*[@id='content']/div")
	private WebElement msg;
	
	public EditPage goToNewAddressBookPage(WebDriver driver){
		newLink.click();
		EditPage page = new EditPage(driver);
		return page;
	}
	
	public GroupPage goToNewGroupPage(WebDriver driver){
		newGroupLink.click();
		GroupPage page = new GroupPage(driver);
		return page;
	}
	
	public BirthdayPage goToBirthdayPage(WebDriver driver){
		birthdayLink.click();
		BirthdayPage page = new BirthdayPage(driver);
		return page;
	}
	
	public IndexPage goToHomePage(WebDriver driver){
		homeLink.click();
		IndexPage page = new IndexPage(driver);
		return page;
	}
	
	public String getMsg(){
		return msg.getText();
	}
	
	public String getBodyText(WebDriver driver){
		WebElement body = driver.findElement(By.tagName("body"));
		return body.getText();
	}
	
	public ViewPage goToViewAddressBookPage(WebDriver driver){
		addressBookLink.click();
		ViewPage page = new ViewPage(driver);
		return page;
	}
	
	public ViewPage goToPrintAddressBookPage(WebDriver driver){
		printLink.click();
		ViewPage page = new ViewPage(driver);
		return page;
	}
}
