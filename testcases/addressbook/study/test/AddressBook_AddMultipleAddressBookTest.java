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

public class AddressBook_AddMultipleAddressBookTest {
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
		EditPage ep = ip.goToNewAddressBookPage(driver);
		ep.addAddressBook("firstname1", "lastname1", "address1", "01056321", "mail1@mail.it", "11", "Giugno", "1981");
		ep.addNext();
		ep.addAddressBook("firstname2", "lastname2", "address2", "01056322", "mail2@mail.it", "12", "Giugno", "1982");
		ep.addNext();
		ep.addAddressBook("firstname3", "lastname3", "address3", "01056323", "mail3@mail.it", "13", "Giugno", "1983");
		ip = ep.goToHomePage(driver);
		assertEquals("Numero di risultati: 3", ip.getSearchResult());
		assertEquals("lastname1",ip.getLastName(1));
		assertEquals("firstname1",ip.getFirstName(1));
		assertEquals("mail1@mail.it", ip.getEmail(1));
		assertEquals("01056321", ip.getTelephone(1));
		assertEquals("lastname2",ip.getLastName(2));
		assertEquals("firstname2",ip.getFirstName(2));
		assertEquals("mail2@mail.it", ip.getEmail(2));
		assertEquals("01056322", ip.getTelephone(2));
		assertEquals("lastname3",ip.getLastName(3));
		assertEquals("firstname3",ip.getFirstName(3));
		assertEquals("mail3@mail.it", ip.getEmail(3));
		assertEquals("01056323", ip.getTelephone(3));
	}
}
