package testcases.addressbook.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DeletePage extends AddressBookPage{
	private WebDriver driver;
	
	public DeletePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
