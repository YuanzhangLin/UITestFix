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
import testcases.addressbook.study.po.GroupPage;
import testcases.addressbook.study.po.IndexPage;

public class AddressBook_UnassignFromMultipleGroupsTest {
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
		GroupPage gp = ip.unassignGroup(1,"NewGroup1");
		ip = gp.goToHomePage(driver);
		gp = ip.unassignGroup(1,"NewGroup2");
		ip = gp.goToHomePage(driver);
		gp = ip.unassignGroup(1,"NewGroup3");
		ip = gp.goToHomePage(driver);
		ip.viewAddressBooksByGroup("NewGroup1");
		assertEquals("Numero di risultati: 0", ip.getSearchResult());
		ip.viewAddressBooksByGroup("NewGroup2");
		assertEquals("Numero di risultati: 0", ip.getSearchResult());
		ip.viewAddressBooksByGroup("NewGroup3");
		assertEquals("Numero di risultati: 0", ip.getSearchResult());
	}
}
