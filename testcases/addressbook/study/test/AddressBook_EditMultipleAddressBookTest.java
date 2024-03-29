package testcases.addressbook.study.test;

import static org.junit.Assert.assertEquals;
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
import testcases.addressbook.study.po.EditPage;
import testcases.addressbook.study.po.IndexPage;

public class AddressBook_EditMultipleAddressBookTest {
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
		EditPage ep = ip.editAddressBookI(1);
		ep.edit("newaddress1", "111111", "newmail1@mail.it");
		ip = ep.goToHomePage(driver);
		ep = ip.editAddressBookI(2);
		ep.edit("newaddress2", "222222", "newmail2@mail.it");
		ip = ep.goToHomePage(driver);
		ep = ip.editAddressBookI(3);
		ep.edit("newaddress3", "333333", "newmail3@mail.it");
		ip = ep.goToHomePage(driver);
		assertEquals("newmail1@mail.it", ip.getEmail(1));
		assertEquals("111111", ip.getTelephone(1));
		assertEquals("newmail2@mail.it", ip.getEmail(2));
		assertEquals("222222", ip.getTelephone(2));
		assertEquals("newmail3@mail.it", ip.getEmail(3));
		assertEquals("333333", ip.getTelephone(3));

	}
}
