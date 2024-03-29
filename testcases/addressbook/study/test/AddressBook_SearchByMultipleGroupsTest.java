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
import testcases.addressbook.study.po.IndexPage;

public class AddressBook_SearchByMultipleGroupsTest {
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
		ip.viewAddressBooksByGroup("Group1");
		assertEquals("Numero di risultati: 1", ip.getSearchResult());
		assertEquals("lastname1",ip.getLastName(1));
		assertEquals("firstname1",ip.getFirstName(1));
		assertEquals("mail1@mail.it", ip.getEmail(1));
		assertEquals("01056321", ip.getTelephone(1));
		ip.viewAddressBooksByGroup("Group2");
		assertEquals("Numero di risultati: 1", ip.getSearchResult());
		assertEquals("lastname2",ip.getLastName(1));
		assertEquals("firstname2",ip.getFirstName(1));
		assertEquals("mail2@mail.it", ip.getEmail(1));
		assertEquals("01056322", ip.getTelephone(1));
		ip.viewAddressBooksByGroup("Group3");
		assertEquals("Numero di risultati: 1", ip.getSearchResult());
		assertEquals("lastname3",ip.getLastName(1));
		assertEquals("firstname3",ip.getFirstName(1));
		assertEquals("mail3@mail.it", ip.getEmail(1));
		assertEquals("01056323", ip.getTelephone(1));
	}
}
