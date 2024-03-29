package testcases.mantisbt.study.test;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Category;
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

import testcases.mantisbt.study.po.*;



public class AddCategoryTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws SQLException, IOException, ClassNotFoundException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		Category.deleteCategory("Category001","Category001");
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
		ManageProjectEditPage mpep = mpp.goToManageProjectEditPage();
		mpep.addCategory("Category001");
		assertEquals("Category001", mpep.getCategoryName());
		mpep.doLogout();
	}
}
