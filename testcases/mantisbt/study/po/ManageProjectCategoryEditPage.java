package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageProjectCategoryEditPage {
	private WebDriver driver;
	@FindBy(css="input.button")
	private WebElement editButton;
	@FindBy(name="new_category")
	private WebElement categoryName;
	
	public ManageProjectCategoryEditPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectCategoryUpdatePage editCategoryName(String name){
		categoryName.clear();
		categoryName.sendKeys(name);
		editButton.click();
		ManageProjectCategoryUpdatePage page = new ManageProjectCategoryUpdatePage(driver);
		return page;
	}
	
	
}
