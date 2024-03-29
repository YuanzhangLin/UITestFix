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

public class AddressBook_EditMultipleGroupsTest {
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
		gp.editGroupI(1, "NewGroup1", "New Header1", "New Footer1");
		gp.goBack();
		gp.editGroupI(1, "NewGroup2", "New Header2", "New Footer2");
		gp.goBack();
		gp.editGroupI(1, "NewGroup3", "New Header3", "New Footer3");
		gp.goBack();
		assertTrue(gp.getGroupsName().contains("NewGroup1"));
		assertTrue(gp.getGroupsName().contains("NewGroup2"));
		assertTrue(gp.getGroupsName().contains("NewGroup3"));
		ip = gp.goToHomePage(driver);
		ip.viewAddressBooksByGroup("NewGroup1");
		assertTrue(gp.getBodyText(driver).contains("New Header1"));
		assertTrue(gp.getBodyText(driver).contains("New Footer1"));
		ip.viewAddressBooksByGroup("NewGroup2");
		assertTrue(gp.getBodyText(driver).contains("New Header2"));
		assertTrue(gp.getBodyText(driver).contains("New Footer2"));
		ip.viewAddressBooksByGroup("NewGroup3");
		assertTrue(gp.getBodyText(driver).contains("New Header3"));
		assertTrue(gp.getBodyText(driver).contains("New Footer3"));
	}
}
