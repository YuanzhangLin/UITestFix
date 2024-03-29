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

import org.testng.Assert;
import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.study.po.*;


public class MRBS_RemoveMultipleEntriesTest {
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
		dp.selectDay("12","Apr","2023");
		ViewEntryPage vep = dp.selectEntry("MyEvent1");
		dp = vep.removeEntry();
		vep = dp.selectEntry("MyEvent2");
		dp = vep.removeEntry();
		vep = dp.selectEntry("MyEvent3");
		dp = vep.removeEntry();
		assertTrue(!dp.getBodyText(driver).contains("MyEvent1"));
		assertTrue(!dp.getBodyText(driver).contains("MyEvent2"));
		assertTrue(!dp.getBodyText(driver).contains("MyEvent3"));
		dp.selectDay("10","May","2023");
		vep = dp.selectEntry("MyEvent1");
		dp = vep.removeEntry();
		assertTrue(!dp.getBodyText(driver).contains("MyEvent1"));
		dp.selectDay("11","May","2023");
		vep = dp.selectEntry("MyEvent2");
		dp = vep.removeEntry();
		assertTrue(!dp.getBodyText(driver).contains("MyEvent2"));
		dp.selectDay("12","May","2023");
		vep = dp.selectEntry("MyEvent3");
		dp = vep.removeEntry();
		assertTrue(!dp.getBodyText(driver).contains("MyEvent3"));
		dp.doLogout();
	}
}
