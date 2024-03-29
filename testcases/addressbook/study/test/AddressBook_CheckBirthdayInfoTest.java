package testcases.addressbook.study.test;

import static org.junit.Assert.*;

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

public class AddressBook_CheckBirthdayInfoTest {
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
		assertEquals("19.",bp.getDay(1));
		assertEquals("lastname",bp.getLastName(1));
		assertEquals("firstname",bp.getFirstName(1));
		assertEquals("mail@mail.it",bp.getEmail(1));
		assertEquals("01056321",bp.getPhone(1));
		assertEquals("Giugno",bp.getMonth());
	}
}
