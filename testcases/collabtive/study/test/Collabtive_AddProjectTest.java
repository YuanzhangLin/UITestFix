package testcases.collabtive.study.test;
import testcases.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testcases.collabtive.model_based_dataset.po.Task;
import testcases.collabtive.study.po.*;
import testcases.collabtive.model_based_dataset.po.*;

public class Collabtive_AddProjectTest {
	public WebDriver driver;
	
	@Before
	public void setUp() throws SQLException, IOException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
		Project.cleanProject();
		Stone.cleanStone();
		Task.cleanTask();
		User.cleanUser();
		Role.cleanProject();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException{
		IndexPage ip = new IndexPage(driver);
		ip.login("admin",Constants.Collabtive_ADMIN_PASSWORD);
		AdminPage ap = ip.goToAdminPage();
		ap.addProject("Project001", "Description for Project001", "10000");
		assertEquals("Project001", ap.getProjectName());
		ManageProjectPage mpp = ap.goToManageProjectPage();
		assertEquals("Budget: 10000", mpp.getProjectBudget());
		assertEquals("Description for Project001", mpp.getProjectDescription());
		ap.doLogout();		
	}
	
	
	
}
