package testcases.mantisbt.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageProjectEditPage extends MantisBTPage {
	private WebDriver driver;
	@FindBy(css="form > input.button")
	private WebElement deleteButton;
	@FindBy(name="category")
	private WebElement categoryName;
	@FindBy(css="td.left > form > input.button")
	private WebElement addCategoryButton;
	@FindBy(xpath="html/body/div[7]/a[1]/table/tbody/tr[3]/td[1]")
	private WebElement category;
	@FindBy(xpath="//input[@value='Delete']")
	private WebElement deleteCategoryButton;
	@FindBy(css="td.center > form > input.button-small")
	private WebElement editCategoryButton;
	@FindBy(name="status")
	private WebElement statusList;
	@FindBy(name="view_state")
	private WebElement view_state;
	@FindBy(name="description")
	private WebElement description;
	@FindBy(css="input.button")
	private WebElement updateButton;
	@FindBy(css="td.form-title > form > input.button-small")
	private WebElement addSubproject;
	@FindBy(xpath="//input[@value='Unlink']")
	private WebElement unlink;
	@FindBy(linkText="Manage Projects")
	private WebElement manageProjectLink;
	@FindBy(name="subproject_id")
	private WebElement subList;
	@FindBy(css="td.left > form > input[type='submit']")
	private WebElement addSub;
	
	public ManageProjectEditPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ManageProjectDeletePage deleteProject(){
		deleteButton.click();
		ManageProjectDeletePage page = new ManageProjectDeletePage(driver);
		return page;
	}
	
	public void addCategory(String name){
		categoryName.sendKeys(name);
		addCategoryButton.click();
	}
	
	public ManageProjectCategoryAddPage addWrongCategory(String name){
		categoryName.sendKeys(name);
		addCategoryButton.click();
		ManageProjectCategoryAddPage page = new ManageProjectCategoryAddPage(driver);
		return page;
	}
	
	public String getCategoryName(){
		return category.getText();
	}

	public ManageProjectCategoryDeletePage deleteCategory(){
		deleteCategoryButton.click();
		ManageProjectCategoryDeletePage page = new ManageProjectCategoryDeletePage(driver);
		return page;
	}
	
	public ManageProjectCategoryEditPage editCategory(){
		editCategoryButton.click();
		ManageProjectCategoryEditPage page = new ManageProjectCategoryEditPage(driver);
		return page;
	}
	
	public ManageProjectPage editStatus(String status){
		new Select(statusList).selectByVisibleText(status);
		updateButton.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	public ManageProjectPage editViewState(String viewstate){
		new Select(view_state).selectByVisibleText(viewstate);
		updateButton.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	public ManageProjectPage editDescription(String descr){
		description.clear();
		description.sendKeys(descr);
		updateButton.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	public ManageProjectCreatePage addSubproject(){
		addSubproject.click();
		ManageProjectCreatePage page = new ManageProjectCreatePage(driver);
		return page;
	}

	public String getSubprojectNameI(int i){
		WebElement sub = driver.findElement(By.xpath("html/body/div[6]/table/tbody/tr["+(i+2)+"]/td[1]/a"));
		return sub.getText();
	}
	
	public boolean isSubprojectIPresent(int i){
		try{
			driver.findElement(By.xpath("html/body/div[6]/table/tbody/tr["+(i+2)+"]/td[1]/a"));
			}
		catch(NoSuchElementException e){
			return false;
		}
		return true;
	}
	
	public ManageProjectSubprojectDeletePage unlinkSubproject(){
		unlink.click();
		ManageProjectSubprojectDeletePage page = new ManageProjectSubprojectDeletePage(driver);
		return page;
	}
	
	public ManageProjectPage goToManageProjectPage(){
		manageProjectLink.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	public ManageProjectSubprojectAddPage addSubproject(String name){
		new Select(subList).selectByVisibleText(name);
		addSub.click();
		ManageProjectSubprojectAddPage page = new ManageProjectSubprojectAddPage(driver);
		return page;
	}
	
}
