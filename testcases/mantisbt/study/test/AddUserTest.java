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

import testcases.mantisbt.model_based_dataset.po.User;
import testcases.mantisbt.study.po.*;


public class AddUserTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws SQLException, IOException, InterruptedException, ClassNotFoundException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		User.clearUser();
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
		ManageUserCreatePage mucp = mup.createNewUser();
		ManageUserCreateProceedPage mucpp = mucp.createUser("username001", "username001", "username001@username.it", 2);
		ManageUserEditPage muep = mucpp.proceed();
//		mup = muep.goToManageUserPage();
		assertEquals("username001", mup.getUsername());
		assertEquals("username001", mup.getRealname());
		assertEquals("username001@username.it", mup.getEmail());
		assertEquals("updater", mup.getAccessLvl());
		mup.doLogout();
	}
}
