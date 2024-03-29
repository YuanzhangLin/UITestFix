package testcases.mrbs.study.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.study.po.*;

public class MRBS_AddMultipleRoomsTest {
	private WebDriver driver;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(MRBSConstants.BASE_URL);
		driver.manage().window().maximize();

	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException{
		DayPage dp = new DayPage(driver);
		AdminPage ap = dp.doLogin(driver);
		dp = ap.login("administrator","secret");
		ap = dp.goToAdminPage();
		ap.selectBuilding(1);
		ap.addRoom("MyRoom1", "Description of MyRoom1", "5");
		ap.addRoom("MyRoom2", "Description of MyRoom2", "12");
		ap.addRoom("MyRoom3", "Description of MyRoom3", "31");
		assertTrue(ap.getRoomInfo(1).contains("MyRoom1(Description of MyRoom1, 5)"));
		assertTrue(ap.getRoomInfo(2).contains("MyRoom2(Description of MyRoom2, 12)"));
		assertTrue(ap.getRoomInfo(3).contains("MyRoom3(Description of MyRoom3, 31)"));
		ap.doLogout();		
	}
}
