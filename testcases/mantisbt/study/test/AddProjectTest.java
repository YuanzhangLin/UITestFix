package testcases.mantisbt.study.test;
import testcases.Constants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import testcases.mantisbt.model_based_dataset.po.Project;
import testcases.mantisbt.study.po.*;


public class AddProjectTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws SQLException, IOException, InterruptedException, ClassNotFoundException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		Project.deleteProject("Project001");
		Project.deleteProject("SubProject001");
		Project.cleanSubProject();
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
		ManageProjectCreateProceedPage mpcpp = mpcp.addNewProject("Project001", 1, 0, "description");
		mpp = mpcpp.proceed();
		assertEquals("Project001", mpp.getProjectName());
		assertEquals("release", mpp.getProjectStatus());
		assertEquals("public", mpp.getProjectViewState());
		assertEquals("description", mpp.getProjectDescription());
		mpp.doLogout();
	}
}
