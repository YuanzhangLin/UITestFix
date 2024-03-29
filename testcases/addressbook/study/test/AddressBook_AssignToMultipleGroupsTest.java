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
import testcases.addressbook.study.po.GroupPage;
import testcases.addressbook.study.po.IndexPage;

public class AddressBook_AssignToMultipleGroupsTest {
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
		GroupPage gp = ip.assignGroup(1,"Group1");
		assertTrue(gp.getMsg().contains("Users added."));	
		ip = gp.goToHomePage(driver);
		gp = ip.assignGroup(2,"Group2");
		assertTrue(gp.getMsg().contains("Users added."));	
		ip = gp.goToHomePage(driver);
		gp = ip.assignGroup(3,"Group3");
		assertTrue(gp.getMsg().contains("Users added."));		
	}
}
