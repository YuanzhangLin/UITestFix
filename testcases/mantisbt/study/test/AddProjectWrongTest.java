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


public class AddProjectWrongTest {
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
		ManageProjectCreateErrorPage mpcep = mpcp.addNewWrongProject("Project001", 1, 0, "description");
		assertEquals("A project with that name already exists. Please go back and enter a different name.", mpcep.getErrorMsg());
		mpcep.doLogout();
	}
}
