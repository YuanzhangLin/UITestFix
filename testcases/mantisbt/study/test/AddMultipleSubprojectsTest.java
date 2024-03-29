package testcases.mantisbt.study.test;
import testcases.Constants;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import testcases.mantisbt.study.po.*;


public class AddMultipleSubprojectsTest {
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
		ManageProjectCreatePage mpcp= mpp.createNewProject();
		ManageProjectCreateProceedPage mpcpp = mpcp.addNewProject("Project1", 1, 0, "");
		mpp = mpcpp.proceed();
		ManageProjectEditPage mpep = mpp.goToManageProjectEditPageI();
		mpcp = mpep.addSubproject();
		mpcpp = mpcp.addNewProject("sub1", 1, 0, "");
		mpp = mpcpp.proceed();
		mpep = mpp.goToManageProjectEditPageI();
		mpcp = mpep.addSubproject();
		mpcpp = mpcp.addNewProject("sub2", 1, 0, "");
		mpp = mpcpp.proceed();
		mpep = mpp.goToManageProjectEditPageI();
		assertEquals(mpep.getSubprojectNameI(1), "sub1");
		assertEquals(mpep.getSubprojectNameI(2), "sub2");
		mpp.doLogout();
	}
}
