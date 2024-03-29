package testcases.mrbs.study.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.mrbs.model_based_dataset.sql.Entry;
import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.study.po.*;


public class MRBS_AddAndRemoveSerialEntryTest {
	private WebDriver driver;
	@Before
	public void setUp() throws SQLException, IOException, ClassNotFoundException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(MRBSConstants.BASE_URL);
		driver.manage().window().maximize();
		Entry.clear();
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	public void t(){
		System.out.println("!");
	}
	@Test
	public void runTest() throws InterruptedException{
		t();
		//aggiunge room, crea evento seriale e verifica che sia presente N giorni
		//poi lo elimina e verifica che non ci sia pi�
		//infine elimina la room
		DayPage dp = new DayPage(driver);
		AdminPage ap = dp.doLogin(driver);
		dp = ap.login("administrator","secret");
		ap = dp.goToAdminPage();
		ap.selectBuilding(1);
		ap.addRoom("LastRoom", "Description of LastRoom", "10");
		dp = ap.goToDayPage(driver);
		EditEntryPage eep = dp.addEntry(1,1);
		dp = eep.addSerialEntry("SerialEvent", "Description for SerialEvent", "1", 5, "2023", "4", 5, "2023", "MyBuilding", "LastRoom");
		assertEquals("SerialEvent", dp.getEntryName(1,1));
		dp.goToNextDay();
		assertEquals("SerialEvent", dp.getEntryName(1,1));
		dp.goToNextDay();
		assertEquals("SerialEvent", dp.getEntryName(1,1));
		ViewEntryPage vep = dp.selectEntry("SerialEvent");
		dp = vep.removeSerialEntry();
		dp.selectDay("1", "Jul", "2023");
		assertTrue(!dp.getBodyText(driver).contains("SerialEvent"));
		dp.goToNextDay();
		assertTrue(!dp.getBodyText(driver).contains("SerialEvent"));
		dp.goToNextDay();
		assertTrue(!dp.getBodyText(driver).contains("SerialEvent"));
		ap = dp.goToAdminPage();
		ap.selectBuilding(1);
		DelPage delp = ap.removeRoom(1);
		ap = delp.remove();
		ap.doLogout();		
	}
}
