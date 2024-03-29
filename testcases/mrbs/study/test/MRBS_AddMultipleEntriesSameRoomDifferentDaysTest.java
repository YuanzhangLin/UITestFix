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

public class MRBS_AddMultipleEntriesSameRoomDifferentDaysTest {
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
		EditEntryPage eep = dp.addEntry(1,1);
		//10, 11, 12 maggio 2023, 3 eventi
		dp = eep.addEntry("MyEvent1", "Description for MyEvent1", "10", 4, "2023", "1", "8", "", "MyBuilding", "MyRoom");
		eep = dp.addEntry(25,1);
		dp = eep.addEntry("MyEvent2", "Description for MyEvent2", "11", 4, "2023", "3", "11", "", "MyBuilding", "MyRoom");
		eep = dp.addEntry(25,1);
		dp = eep.addEntry("MyEvent3", "Description for MyEvent3", "12", 4, "2023", "2", "15", "", "MyBuilding", "MyRoom");
		dp.selectDay("10", "May", "2023");
		assertTrue(dp.getBodyText(driver).contains("MyEvent1"));
		dp.selectDay("11", "May", "2023");
		assertTrue(dp.getBodyText(driver).contains("MyEvent2"));
		dp.selectDay("12", "May", "2023");
		assertTrue(dp.getBodyText(driver).contains("MyEvent3"));
		dp.doLogout();		
	}
}
