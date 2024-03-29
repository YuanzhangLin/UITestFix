package testcases.mantisbt.study.test;
import testcases.Constants;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.mantisbt.study.po.*;


public class AddMultipleUsersTest {
private WebDriver driver;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.getMantisUrl());
		driver.manage().window().maximize();
	}
	
	@After 
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void doTest(){
		LoginPage lp= new LoginPage(driver);
		MyViewPage mvp = lp.login("administrator", "root");		ManageUserPage mup = mvp.goToManageUserPage();
		ManageUserCreatePage mucp = mup.createNewUser();
		ManageUserCreateProceedPage mucpp = mucp.createUser("user001", "user001", "user001@user.it", 0);
		ManageUserEditPage muep = mucpp.proceed();
		mup = muep.goToManageUserPage();
		mucp = mup.createNewUser();
		mucpp = mucp.createUser("user002", "user002", "user002@user.it", 1);
		muep = mucpp.proceed();
		mup = muep.goToManageUserPage();
		mucp = mup.createNewUser();
		mucpp = mucp.createUser("user003", "user003", "user003@user.it", 2);
		muep = mucpp.proceed();
		mup = muep.goToManageUserPage();
		assertEquals("user001", mup.getUsernameI(1));
		assertEquals("viewer", mup.getAccessLvlI(1));
		assertEquals("user002", mup.getUsernameI(2));
		assertEquals("reporter", mup.getAccessLvlI(2));
		assertEquals("user003", mup.getUsernameI(3));
		assertEquals("updater", mup.getAccessLvlI(3));
		mup.doLogout();
	}
}
