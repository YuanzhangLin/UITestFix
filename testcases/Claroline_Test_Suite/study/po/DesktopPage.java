package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DesktopPage extends ClarolinePage{
	private WebDriver driver;
	@FindBy(linkText="Platform administration")
	private WebElement platformAdminLink;
	@FindBy(id="userProfileTitle")
	private WebElement userProfile;
	@FindBy(linkText="Enrol on a new course")
	private WebElement enrolLink;
	@FindBy(linkText="Remove course enrolment")
	private WebElement removeEnrolLink;
	@FindBy(linkText="001 - Course001")
	private WebElement course001;
	@FindBy(linkText=">>")
	private WebElement nextMonthAgendaLink;
	@FindBy(linkText="My User Account")
	private WebElement userAccountLink;
	
	public DesktopPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AdminPage goToAdminPage(){
		platformAdminLink.click();
		AdminPage page = new AdminPage(driver);
		return page;
	}
	
	public String getUserProfile(){
		return userProfile.getText();
	}
	
	public AuthCoursesPage enrolToCourse(){
		enrolLink.click();
		AuthCoursesPage page = new AuthCoursesPage(driver);
		return page;
	}
	
	public AuthCoursesPage removeEnrolFromCourse(){
		removeEnrolLink.click();
		AuthCoursesPage page = new AuthCoursesPage(driver);
		return page;
	}
	
	public CourseIndexPage goInsideCourse() throws InterruptedException{
		course001.click();
		Thread.sleep(2000);
		CourseIndexPage page = new CourseIndexPage(driver);
		return page;
	}
	
	public void nextMonthAgenda() throws InterruptedException{
		nextMonthAgendaLink.click();
		Thread.sleep(3000);
	}
	
	public ProfilePage goToMyUserAccount(){
		userAccountLink.click();
		ProfilePage page = new ProfilePage(driver);
		return page;
	}
	
}
