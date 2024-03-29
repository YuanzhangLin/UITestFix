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

public class AddressBook_AddMultipleGroupsTest {
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
		GroupPage gp = ip.goToNewGroupPage(driver);
		gp.addNewGroup("Group1", "Header1", "Footer1");
		gp.goBack();
		gp.addNewGroup("Group2", "Header2", "Footer2");
		gp.goBack();
		gp.addNewGroup("Group3", "Header3", "Footer3");
		gp.goBack();
		assertTrue(gp.getGroupsName().contains("Group1"));
		assertTrue(gp.getGroupsName().contains("Group2"));
		assertTrue(gp.getGroupsName().contains("Group3"));
	}
}
