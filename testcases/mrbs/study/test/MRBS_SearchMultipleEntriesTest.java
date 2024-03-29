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


public class MRBS_SearchMultipleEntriesTest {
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
		SearchPage sp = dp.searchEntry(driver, "MyEvent");
		assertEquals("MyEvent1", sp.getEntryName(1));
		assertEquals("Description for MyEvent1", sp.getEntryDescription(1));
		assertEquals("MyEvent2", sp.getEntryName(2));
		assertEquals("Description for MyEvent2", sp.getEntryDescription(2));
		assertEquals("MyEvent3", sp.getEntryName(3));
		assertEquals("Description for MyEvent3", sp.getEntryDescription(3));
		dp = sp.goToDayPage(driver);
		dp.doLogout();		
	}
}
