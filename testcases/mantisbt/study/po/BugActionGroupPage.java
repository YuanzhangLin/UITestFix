package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugActionGroupPage {
	private WebDriver driver;
	@FindBy(css="input.button")
	private WebElement confirmButton;
	
	public BugActionGroupPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ViewAllBugPage confirmAction(){
		confirmButton.click();
		ViewAllBugPage page = new ViewAllBugPage(driver);
		return page;
	}
}
