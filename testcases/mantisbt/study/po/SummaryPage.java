package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SummaryPage extends MantisBTPage{
	private WebDriver driver;
	@FindBy(xpath="html/body/table[3]/tbody/tr[2]/td[1]/table[1]/tbody/tr[2]/td[2]")
	private WebElement openIssueCounter;
	
	public SummaryPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getOpenIssueCounter(){
		return openIssueCounter.getText();
	}
}
