package testcases.addressbook.study.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import testcases.Constants;
import testcases.addressbook.study.po.EditPage;
import testcases.addressbook.study.po.IndexPage;

public class AddressBook_AddAddressBookTest {
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
		ep.addAddressBook("firstname", "lastname", "address", "01056321", "mail@mail.it", "19", "Giugno", "1985");
		assertTrue(ep.getMsg().contains("Information entered into address book."));
	}
	
	
}
