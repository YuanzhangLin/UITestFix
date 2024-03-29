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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.collabtive.study.po.*;

public class Collabtive_AddUserAlreadyPresentTest {
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
		ap.showUserFrame();
		ap.addUser("username001", "username001@username.it", "password001", "Admin");
		assertTrue(ap.getBodyText(driver).contains("The email address username001@username.it or the username username001 already exists in the Userdatabase.\nPlease go back and correct the fault."));
		ap.doLogout();
	}
}
