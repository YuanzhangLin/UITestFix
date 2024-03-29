package testcases.addressbook.study.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.Constants;
import testcases.addressbook.study.po.BirthdayPage;
import testcases.addressbook.study.po.IndexPage;

public class AddressBook_CheckMultipleBirthdaysInfoTest {
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
		BirthdayPage bp = ip.goToBirthdayPage(driver);
		assertEquals("11.",bp.getDay(1));
		assertEquals("lastname1",bp.getLastName(1));
		assertEquals("firstname1",bp.getFirstName(1));
		assertEquals("mail1@mail.it",bp.getEmail(1));
		assertEquals("01056321",bp.getPhone(1));
		assertEquals("Giugno",bp.getMonth());
		assertEquals("12.",bp.getDay(2));
		assertEquals("lastname2",bp.getLastName(2));
		assertEquals("firstname2",bp.getFirstName(2));
		assertEquals("mail2@mail.it",bp.getEmail(2));
		assertEquals("01056322",bp.getPhone(2));
		assertEquals("Giugno",bp.getMonth());
		assertEquals("13.",bp.getDay(3));
		assertEquals("lastname3",bp.getLastName(3));
		assertEquals("firstname3",bp.getFirstName(3));
		assertEquals("mail3@mail.it",bp.getEmail(3));
		assertEquals("01056323",bp.getPhone(3));
		assertEquals("Giugno",bp.getMonth());
	}
}
