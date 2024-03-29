package testcases.mrbs.study.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.study.po.*;

public class MRBS_CheckMultipleEntriesTest {
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
		dp.selectDay("12", "Apr", "2023");
		assertEquals("Wednesday 12 April 2023", dp.getDate());
		assertEquals("MyEvent1", dp.getEntryName(1,1));
		assertEquals("MyEvent2", dp.getEntryName(1,2));
		assertEquals("MyEvent3", dp.getEntryName(1,3));
		dp.selectDay("10", "May", "2023");
		assertEquals("Wednesday 10 May 2023", dp.getDate());
		assertEquals("MyEvent1", dp.getEntryName(1,1));
		dp.selectDay("11", "May", "2023");
		assertEquals("Thursday 11 May 2023", dp.getDate());
		assertEquals("MyEvent2", dp.getEntryName(7,2));
		dp.selectDay("12", "May", "2023");
		assertEquals("Friday 12 May 2023", dp.getDate());
		assertEquals("MyEvent3", dp.getEntryName(13,3));
		dp.doLogout();
	}
}
