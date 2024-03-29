package testcases.mantisbt.study.test;
import testcases.Constants;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.mantisbt.study.po.*;


public class UnlinkMultipleSubprojectsTest {
	private WebDriver driver;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.getMantisUrl());
		driver.manage().window().maximize();
	}
	
	@After 
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void doTest(){
		LoginPage lp= new LoginPage(driver);
		MyViewPage mvp = lp.login("administrator", "root");		ManageUserPage mup = mvp.goToManageUserPage();
		ManageProjectPage mpp = mup.goToManageProjectPage();
		ManageProjectEditPage mpep = mpp.goToManageProjectEditPageI();
		ManageProjectSubprojectDeletePage mpsdp = mpep.unlinkSubproject();
		mpep = mpsdp.proceed();
		mpsdp = mpep.unlinkSubproject();
		mpep = mpsdp.proceed();
		driver.navigate().refresh();
		assertFalse(mpep.isSubprojectIPresent(1));
		assertFalse(mpep.isSubprojectIPresent(2));
		mpp = mpep.goToManageProjectPage();
		assertEquals("sub1", mpp.getProjectNameI(1));
		assertEquals("sub2", mpp.getProjectNameI(2));
		mpp.doLogout();
	}
}
