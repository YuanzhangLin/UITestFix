package testcases.collabtive.study.test;
import testcases.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.collabtive.study.po.*;

public class Collabtive_OpenTasksProjectPercentageTest {
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
		mtp.showClosedTasks();
		mtp.openTaskN(1);
		mpp = mtp.goToManageProjectPage();
		assertEquals("50%", mpp.getPercentageStatus());
		mtp = mpp.goToManageTaskPage();
		mtp.showClosedTasks();
		mtp.openTaskN(1);
		mpp = mtp.goToManageProjectPage();
		assertEquals("0%", mpp.getPercentageStatus());
		mpp.doLogout();
	}
}