package testcases.mrbs.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditEntryHandlerPage extends MRBSPage{
	private WebDriver driver;	
	@FindBy(xpath="html/body/h2")
	private WebElement msg;
	
	public EditEntryHandlerPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getMsg(){
		return msg.getText();
	}

}
