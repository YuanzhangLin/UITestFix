package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends ClarolinePage{
	private WebDriver driver;
	@FindBy(linkText="Create user")
	private WebElement createUserLink;
	@FindBy(linkText="Create course")
	private WebElement createCourseLink;
	@FindBy(id="search_user")
	private WebElement userSearchBox;
	@FindBy(css="input[type='submit']")
	private WebElement userSearchButton;
	@FindBy(id="search_course")
	private WebElement courseSearchBox;
	@FindBy(css="form[name='searchCourse'] > input[type='submit']")
	private WebElement courseSearchButton;
	@FindBy(linkText="User list")
	private WebElement userListLink;
	@FindBy(linkText="Advanced")
	private WebElement advancedSearchButton;
	@FindBy(css="form[name='searchCourse'] > small > a")
	private WebElement advancedCourseSearchButton;
	
	
	public AdminPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AdminAddNewUserPage addUser() throws InterruptedException{
		createUserLink.click();
		Thread.sleep(1000);
		AdminAddNewUserPage page = new AdminAddNewUserPage(driver);
		return page;
	}
	
	public AdminUsersPage searchUser(String userName){
		userSearchBox.sendKeys(userName);
		userSearchButton.click();
		AdminUsersPage page = new AdminUsersPage(driver);
		return page;
	}
	
	public CourseCreatePage addCourse() throws InterruptedException{
		createCourseLink.click();
		Thread.sleep(1000);
		CourseCreatePage page = new CourseCreatePage(driver);
		return page;
	}
	
	public AdminCoursesPage searchCourse(String courseName) throws InterruptedException{
		Thread.sleep(1000);
		courseSearchBox.sendKeys(courseName);
		courseSearchButton.click();
		AdminCoursesPage page = new AdminCoursesPage(driver);
		return page;
	}
	
	public AdminUsersPage goToUsersList(){
		userListLink.click();
		AdminUsersPage page = new AdminUsersPage(driver);
		return page;
	}
	
	public AdvancedUserSearchPage advancedSearch(){
		advancedSearchButton.click();
		AdvancedUserSearchPage page = new AdvancedUserSearchPage(driver);
		return page;
	}
	
	public AdvancedCourseSearchPage advancedCourseSearch(){
		advancedCourseSearchButton.click();
		AdvancedCourseSearchPage page = new AdvancedCourseSearchPage(driver);
		return page;
	}
}
