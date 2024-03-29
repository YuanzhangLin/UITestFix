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

import testcases.collabtive.model_based_dataset.po.User;
import testcases.collabtive.study.po.*;

public class Collabtive_AddUserTest {
	public WebDriver driver;
	
	@Before
	public void setUp() throws SQLException, IOException, InterruptedException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
		User.deleteUser("username001");
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
		ap.showUserFrame();
		ap.addUser("username001", "username001@username.it", "password001", "Admin");
		ap.openEditUserFrame();
		assertEquals("username001", ap.getUserName());
		assertEquals("username001@username.it", ap.getUserEmail());
		assertEquals("Admin", ap.getUserRole(2));
		ap.doLogout();	
	}
}
