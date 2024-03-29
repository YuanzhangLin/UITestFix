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

import testcases.collabtive.study.po.*;


public class Collabtive_AddTasklistTest {
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
		mtp.addTaskList("Tasklist001", "Milestone001");
		assertEquals("Tasklist001", mtp.getTasklistName());
		ManageMilestonePage mmp = mtp.goToMilestonePage();
		mmp.getMilestoneDetails();
		assertEquals("Tasklist001", mmp.getTasklistName());
		mmp.doLogout();
	}
}
