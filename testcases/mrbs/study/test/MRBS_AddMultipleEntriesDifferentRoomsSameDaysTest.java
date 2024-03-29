package testcases.mrbs.study.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.study.po.*;

public class MRBS_AddMultipleEntriesDifferentRoomsSameDaysTest {
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
		//tutti il 12 aprile su room diverse
		dp = eep.addEntry("MyEvent1", "Description for MyEvent1", "12", 3, "2023", "1", "7", "", "MyBuilding", "MyRoom1");
		eep = dp.addEntry(1,2);
		dp = eep.addEntry("MyEvent2", "Description for MyEvent2", "12", 3, "2023", "1", "7", "", "MyBuilding", "MyRoom2");
		eep = dp.addEntry(1,3);
		dp = eep.addEntry("MyEvent3", "Description for MyEvent3", "12", 3, "2023", "1", "7", "", "MyBuilding", "MyRoom3");
		dp.selectDay("12", "Apr", "2023");
		assertEquals("MyEvent1", dp.getEntryName(1,1));
		assertEquals("MyEvent2", dp.getEntryName(1,2));
		assertEquals("MyEvent3", dp.getEntryName(1,3));
		dp.doLogout();		
	}
}
