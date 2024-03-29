package testcases.addressbook.study.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.Constants;
import testcases.addressbook.study.po.IndexPage;
import testcases.addressbook.study.po.ViewPage;

public class AddressBook_CheckMultipleAddressBookTest {
	private WebDriver driver;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(Constants.getAddressBookUrl());
	}


	@After
	public void tearDown(){
		driver.quit();
	}

	@Test
	public void runTest() throws InterruptedException{
		IndexPage ip = new IndexPage(driver);
		ViewPage vp = ip.goToViewAddressBookPage(driver);
		assertTrue(vp.getAddressBookContent(1).contains("firstname1"));
		assertTrue(vp.getAddressBookContent(1).contains("lastname1"));
		assertTrue(vp.getAddressBookContent(1).contains("01056321"));
		assertTrue(vp.getAddressBookContent(1).contains("11"));
		assertTrue(vp.getAddressBookContent(1).contains("Giugno"));
		assertTrue(vp.getAddressBookContent(1).contains("1981"));
		assertTrue(vp.getAddressBookContent(2).contains("firstname2"));
		assertTrue(vp.getAddressBookContent(2).contains("lastname2"));
		assertTrue(vp.getAddressBookContent(2).contains("01056322"));
		assertTrue(vp.getAddressBookContent(2).contains("12"));
		assertTrue(vp.getAddressBookContent(2).contains("Giugno"));
		assertTrue(vp.getAddressBookContent(2).contains("1982"));
		assertTrue(vp.getAddressBookContent(3).contains("firstname3"));
		assertTrue(vp.getAddressBookContent(3).contains("lastname3"));
		assertTrue(vp.getAddressBookContent(3).contains("01056323"));
		assertTrue(vp.getAddressBookContent(3).contains("13"));
		assertTrue(vp.getAddressBookContent(3).contains("Giugno"));
		assertTrue(vp.getAddressBookContent(3).contains("1983"));
	}
}
