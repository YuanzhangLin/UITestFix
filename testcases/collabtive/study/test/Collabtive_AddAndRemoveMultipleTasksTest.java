package testcases.collabtive.study.test;
import testcases.Constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import testcases.Constants;

import testcases.collabtive.study.po.*;

public class Collabtive_AddAndRemoveMultipleTasksTest {
	public WebDriver driver;
	
	@Before
	public void setUp(){
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException{
		IndexPage ip = new IndexPage(driver);
		ip.login("admin",Constants.Collabtive_ADMIN_PASSWORD);
		AdminPage ap = ip.goToAdminPage();
		ManageProjectPage mpp = ap.goToManageProjectPage();
		ManageTaskPage mtp = mpp.goToManageTaskPage();
		mtp.addTask("mytask1", "admin");
		mtp.addTask("mytask2", "admin");
		mtp.addTask("mytask3", "admin");
		assertTrue(mtp.getBodyText(driver).contains("mytask1"));
		assertTrue(mtp.getBodyText(driver).contains("mytask2"));
		assertTrue(mtp.getBodyText(driver).contains("mytask3"));
		Alert alert = mtp.removeTaskN(1);
		Thread.sleep(2000);
		alert.accept();
		alert = mtp.removeTaskN(2);
		Thread.sleep(2000);
		alert.accept();
		alert = mtp.removeTaskN(3);
		Thread.sleep(2000);
		alert.accept();
		driver.navigate().refresh();
		assertFalse(mtp.getBodyText(driver).contains("mytask1"));
		assertFalse(mtp.getBodyText(driver).contains("mytask2"));
		assertFalse(mtp.getBodyText(driver).contains("mytask3"));
		mtp.doLogout();
	}
}
