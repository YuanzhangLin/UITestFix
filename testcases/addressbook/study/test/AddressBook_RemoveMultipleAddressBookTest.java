package testcases.addressbook.study.test;

import static org.junit.Assert.*;
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
import testcases.addressbook.study.po.DeletePage;
import testcases.addressbook.study.po.EditPage;
import testcases.addressbook.study.po.IndexPage;

public class AddressBook_RemoveMultipleAddressBookTest {
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
		DeletePage dp = ep.removeAddressBook();
		ip = dp.goToHomePage(driver);
		assertTrue(!ip.getBodyText(driver).contains("firstname1"));
		assertEquals("Numero di risultati: 2",ip.getSearchResult());
		ep = ip.editAddressBookI(1);
		dp = ep.removeAddressBook();
		ip = dp.goToHomePage(driver);
		assertTrue(!ip.getBodyText(driver).contains("firstname2"));
		assertEquals("Numero di risultati: 1",ip.getSearchResult());
		ep = ip.editAddressBookI(1);
		dp = ep.removeAddressBook();
		ip = dp.goToHomePage(driver);
		assertTrue(!ip.getBodyText(driver).contains("firstname3"));
		assertEquals("Numero di risultati: 0",ip.getSearchResult());
	}
}
