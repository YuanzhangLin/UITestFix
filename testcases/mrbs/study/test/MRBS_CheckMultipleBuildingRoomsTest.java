package testcases.mrbs.study.test;

import static org.junit.Assert.assertEquals;

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
public class MRBS_CheckMultipleBuildingRoomsTest {
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
		dp.selectBuilding("MyBuilding");
		assertEquals("MyRoom1(5)", dp.getRoomName(1));
		assertEquals("MyRoom2(12)", dp.getRoomName(2));
		assertEquals("MyRoom3(31)", dp.getRoomName(3));
		dp.doLogout();
	}
}