package testcases.mrbs.study.test;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.study.po.*;

import static org.junit.Assert.*;

public class MRBS_AddLongNameRoomNegativeTest {
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
		ap.addRoom("LongMoreThanTwentyFiveCharactersRoomName","","");
//		assertFalse(ap.getRoomInfo(1).contains("LongMoreThanTwentyFiveCharactersRoomName"));
//		assertTrue(ap.getRoomInfo(1).contains("LongMoreThanTwentyFiveCha"));
//		DelPage delp = ap.removeRoom(1);
//		ap = delp.remove();
		assertEquals("Data too long for column 'room_name' at row 1", driver.findElement(By.xpath("/html/body/p[1]")).getText());
		ap.doLogout();
	}
}
