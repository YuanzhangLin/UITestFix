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


public class Collabtive_AddAndRemoveLateMilestoneTest {
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
		ManageMilestonePage mmp = mpp.goToManageMilestoneLink();
		mmp.addLateMilestone("Milestone002","20.03.2012");
		assertEquals("Milestone002", mmp.getLateMilestoneName());
		assertEquals("20.03.2012", mmp.getLateMilestoneDate());
		Alert alert = mmp.removeLateMilestone();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
		driver.navigate().refresh();
		assertTrue(!mmp.getBodyText(driver).contains("Milestone002"));
		assertTrue(!mmp.getBodyText(driver).contains("20.03.2012"));
		mmp.doLogout();
	}
}
