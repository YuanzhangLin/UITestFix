package testcases.mantisbt.study.test;
import testcases.Constants;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.mantisbt.study.po.*;


public class DeleteMultipleUsersTest {
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
		ManageUserEditPage muep = mup.goToManageUserEditPageI();
		ManageUserDeletePage mudp = muep.deleteUser();
		mup = mudp.delete();
		muep = mup.goToManageUserEditPageI();
		mudp = muep.deleteUser();
		mup = mudp.delete();
		muep = mup.goToManageUserEditPageI();
		mudp = muep.deleteUser();
		mup = mudp.delete();
		assertEquals("Manage Accounts [1]",mup.getAccountListI());
		assertTrue(!mup.getBodyText(driver).contains("user001"));
		assertTrue(!mup.getBodyText(driver).contains("user002"));
		assertTrue(!mup.getBodyText(driver).contains("user003"));
		mup.doLogout();
	}
}
