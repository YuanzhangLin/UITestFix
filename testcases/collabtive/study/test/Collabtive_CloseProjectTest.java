package testcases.collabtive.study.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testcases.Constants;
import testcases.collabtive.study.po.*;

public class Collabtive_CloseProjectTest {
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
		ip.login("admin", Constants.Collabtive_ADMIN_PASSWORD);
		AdminPage ap = ip.goToAdminPage();
		ap.closeProject();
		ap.showClosedProject();
		assertEquals("Project001", ap.getClosedProjectName());
		assertEquals("Open", ap.getReverseClosedProjectTitle());
		ap.doLogout();
	}
}