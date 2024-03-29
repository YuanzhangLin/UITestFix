package testcases.collabtive.study.test;
import testcases.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.collabtive.study.po.*;

public class Collabtive_RemoveMultipleProjectsTest {
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
		Alert deleteAlert = ap.deleteProjectN(1);
		Thread.sleep(2000);
		deleteAlert.accept();
		deleteAlert = ap.deleteProjectN(2);
		Thread.sleep(2000);
		deleteAlert.accept();
		deleteAlert = ap.deleteProjectN(3);
		Thread.sleep(2000);
		deleteAlert.accept();
		driver.navigate().refresh();
		assertTrue(!ap.getBodyText(driver).contains("myProject1"));
		assertTrue(!ap.getBodyText(driver).contains("myProject2"));
		assertTrue(!ap.getBodyText(driver).contains("myProject3"));
		ap.doLogout();
	}
}
