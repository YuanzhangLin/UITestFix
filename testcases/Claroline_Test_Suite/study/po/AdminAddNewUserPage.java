package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminAddNewUserPage extends ClarolinePage{
	private WebDriver driver;
	@FindBy(id="lastname")
	private WebElement lastName;
	@FindBy(id="firstname")
	private WebElement firstName;
	@FindBy(id="username")
	private WebElement userName;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="password_conf")
	private WebElement passwordConfirm;
	@FindBy(id="email")
	private WebElement email;
	@FindBy(id="student")
	private WebElement studentRole;
	@FindBy(id="courseManager")
	private WebElement teacherRole;
	@FindBy(id="platformAdmin")
	private WebElement adminRole;
	@FindBy(id="applyChange")
	private WebElement confirm;
	@FindBy(linkText="Create another new user")
	private WebElement addNewUserLink;
	@FindBy(linkText="Back to admin page")
	private WebElement backToAdminLink;
	
	public AdminAddNewUserPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addNewStudent(String last, String first, String user, String pass, String passConf) throws InterruptedException{
		Thread.sleep(2000);
		lastName.sendKeys(last);
		firstName.sendKeys(first);
		userName.sendKeys(user);
		password.sendKeys(pass);
		passwordConfirm.sendKeys(passConf);
		studentRole.click();
		confirm.click();
		Thread.sleep(2000);
	}
	
	public void addNewEmailStudent(String last, String first, String user, String pass, String passConf, String mail) throws InterruptedException{
		Thread.sleep(2000);
		lastName.sendKeys(last);
		firstName.sendKeys(first);
		userName.sendKeys(user);
		password.sendKeys(pass);
		passwordConfirm.sendKeys(passConf);
		email.sendKeys(mail);
		studentRole.click();
		confirm.click();
		Thread.sleep(2000);
	}
	
	public void addNewTeacher(String last, String first, String user, String pass, String passConf) throws InterruptedException{
		Thread.sleep(2000);
		lastName.sendKeys(last);
		firstName.sendKeys(first);
		userName.sendKeys(user);
		password.sendKeys(pass);
		passwordConfirm.sendKeys(passConf);
		teacherRole.click();
		confirm.click();
		Thread.sleep(2000);
	}
	
	public void addNewAdmin(String last, String first, String user, String pass, String passConf) throws InterruptedException{
		Thread.sleep(2000);
		lastName.sendKeys(last);
		firstName.sendKeys(first);
		userName.sendKeys(user);
		password.sendKeys(pass);
		passwordConfirm.sendKeys(passConf);
		adminRole.click();
		confirm.click();
		Thread.sleep(2000);
	}
	
	public void addNewUser() throws InterruptedException{
		addNewUserLink.click();
		Thread.sleep(2000);
	}
	
	public AdminPage backToAdmin(){
		backToAdminLink.click();
		AdminPage page = new AdminPage(driver);
		return page;
	}
	
}


