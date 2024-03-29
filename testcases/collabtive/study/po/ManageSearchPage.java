package testcases.collabtive.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageSearchPage extends CollabtivePage{
	private WebDriver driver;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[1]/h2")
	private WebElement searchResult;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/ul/li/div/table/tbody/tr[2]/td/span/a")
	private WebElement searchedProjectName;
	
	public ManageSearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public String getResult(){
		return searchResult.getText();
	}
	
	public String getSearchedProjectName(){
		return searchedProjectName.getText();
	}
	
	
}
