package testcases.collabtive.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage extends CollabtivePage {
	private WebDriver driver;
	@FindBy(id="add_butn_myprojects")
	private WebElement addButton;
	@FindBy(id="name")
	private WebElement name;
	@FindBy(id="tinymce")
	private WebElement description;
	@FindBy(id="budget")
	private WebElement budget;
	@FindBy(css="button[type='submit']")
	private WebElement confirmProject;
	@FindBy(linkText="Project001")
	private WebElement projectName;
	@FindBy(css="a.tool_del")
	private WebElement deleteButton;
	@FindBy(xpath=".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")
	private WebElement userFrame;
	@FindBy(id="add_butn_member")
	private WebElement addUserButton;
	@FindBy(id="name")
	private WebElement userName;
	@FindBy(id="email")
	private WebElement userEmail;
	@FindBy(id="pass")
	private WebElement userPass;
	@FindBy(id="roleselect")
	private WebElement userRole;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[1]/div/form/fieldset/div[10]/div/button")
	private WebElement confirmUser;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div/div/ul/li[1]/div/table/tbody/tr[1]/td[2]/a/img")
	private WebElement userIcon;
	@FindBy(css="a.edit")
	private WebElement editUserButton;
	@FindBy(css="a.del")
	private WebElement removeUserButton;
	@FindBy(xpath="(//input[@id='name'])[2]")
	private WebElement roleName;
	@FindBy(name="permissions_projects[add]")
	private WebElement rolePermAdd;
	@FindBy(name="permissions_projects[edit]")
	private WebElement rolePermEdit;
	@FindBy(name="permissions_projects[del]")
	private WebElement rolePermDel;
	@FindBy(name="permissions_projects[edit]")
	private WebElement rolePermClose;
	@FindBy(css="div.row-butn-bottom > button[type='submit']")
	private WebElement confirmRole;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[1]/td[2]/div/a")
	private WebElement firstNewRoleAdded;		
	@FindBy(css="a.tool_del")
	private WebElement removeRoleButton;
	@FindBy(css="a.butn_check")
	private WebElement closeProjectButton;
	@FindBy(xpath="html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[1]/a")
	private WebElement openProjectButton;
	@FindBy(id="donebutn")
	private WebElement showProjectButton;
	@FindBy(css="a.tool_edit")
	private WebElement editProjectButton;
	
	
	public AdminPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void addProject(String name, String descr, String budg) throws InterruptedException{
		addButton.click();
		Thread.sleep(2000);
		this.name.sendKeys(name);
		driver.switchTo().frame("desc_ifr");
		description.sendKeys(descr);
		driver.switchTo().defaultContent();
		budget.sendKeys(budg);
		confirmProject.click();
		Thread.sleep(2000);
	}
	
	/*public String getProjectName(){
		return projectName.getText();
	}*/
	
	public String getProjectName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[2]/div/a")).getText();
	}
	
	public ManageProjectPage goToManageProjectPage() throws InterruptedException{
		projectName.click();
		Thread.sleep(2000);
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	public Alert deleteProject(){
		deleteButton.click();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public Alert deleteProjectN(int n){
		WebElement delete = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody["+n+"]/tr[1]/td[5]/a[2]"));
		delete.click();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public void showUserFrame(){
		userFrame.click();
	}
	
	public void addUser(String name, String email, String password, String role ) throws InterruptedException{
		addUserButton.click();
		Thread.sleep(2000);
		userName.sendKeys(name);
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		new Select(userRole).selectByVisibleText(role);
		confirmUser.click();
		Thread.sleep(2000);
	}
	
	public void openEditUserFrame(){
		Actions a = new Actions(driver);
		a.moveToElement(userIcon).perform();
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(("a.edit"))));
		editUserButton.click();
	}
	
	public void editUserRole(String role) throws InterruptedException{
		WebElement roleSelect = driver.findElement(By.name("role"));
		new Select(roleSelect).selectByVisibleText(role);
		WebElement confirm = driver.findElement(By.cssSelector("button[type='submit']"));
		confirm.click();
		Thread.sleep(2000);
	}
	
	public String getUserName(){
		return userName.getAttribute("value");
	}
	
	public String getUserEmail(){
		return userEmail.getAttribute("value");
	}
	
	public String getUserRole(int i){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[1]/form/fieldset/table/tbody/tr/td[2]/div/div/table/tbody[22]/tr/td[2]/select/option["+i+"]")).getText();
	}
	
	public Alert removeUser(){
		Actions a = new Actions(driver);
		a.moveToElement(userIcon).perform();
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(("a.del"))));
		removeUserButton.click();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public void addRole(String rolename) throws InterruptedException{
		addButton.click();
		Thread.sleep(2000);
		roleName.sendKeys(rolename);
		rolePermAdd.click();
		rolePermEdit.click();
		rolePermDel.click();
		rolePermClose.click();
		confirmRole.click();
		Thread.sleep(2000);
	}
	
	public String getFirstNewRoleAddedName(){
		return firstNewRoleAdded.getText();
	}
	
	public void editRole(String rolename) throws InterruptedException{
		Thread.sleep(2000);
		WebElement roleName = driver.findElement(By.id("rolename"));
		roleName.clear();
		roleName.sendKeys(rolename);
		WebElement roleEdit = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[3]/div[3]/input"));
		WebElement roleClose = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[3]/div[5]/input"));
		roleEdit.click();
		roleClose.click();
		WebElement confirm = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[5]/button[1]"));
		confirm.click();
		Thread.sleep(2000);		
	}
	
	public void getRoleDetails(){
		firstNewRoleAdded.click();
	}
	
	public boolean isNewRoleAddedEditPermissionChecked(){
		return rolePermEdit.isSelected();
	}
	
	public boolean isNewRoleAddedClosePermissionChecked(){
		return rolePermClose.isSelected();
	}
	
	public Alert removeRole(){
		removeRoleButton.click();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public void closeProject() throws InterruptedException{
		closeProjectButton.click();
		Thread.sleep(5000);
	}
	
	public void showClosedProject() throws InterruptedException{
		driver.navigate().refresh();
		showProjectButton.click();
		Thread.sleep(5000);
	}
	
	public String getClosedProjectName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[2]/div/a")).getText();
	}										
	
	public String getReverseClosedProjectTitle(){
		WebElement checkElement = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[1]/a"));
		return checkElement.getAttribute("title");
	}
	
	public ManageProjectPage openProject() throws InterruptedException{
		openProjectButton.click();
		Thread.sleep(5000);
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
	
	public String getOpenProjectName(){
		return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[2]/div/a")).getText();
	}										
	
	public String getReverseOpenProjectTitle(){
		WebElement checkElement = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[1]/a"));
		return checkElement.getAttribute("title");
	}
	
	public ManageProjectPage editProject(){
		editProjectButton.click();
		ManageProjectPage page = new ManageProjectPage(driver);
		return page;
	}
}
