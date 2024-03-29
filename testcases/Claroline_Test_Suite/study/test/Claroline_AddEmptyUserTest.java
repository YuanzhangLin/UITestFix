package testcases.Claroline_Test_Suite.study.test;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.study.po.AdminAddNewUserPage;
import testcases.Claroline_Test_Suite.study.po.AdminPage;
import testcases.Claroline_Test_Suite.study.po.DesktopPage;
import testcases.Claroline_Test_Suite.study.po.IndexPage;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class Claroline_AddEmptyUserTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws IOException{
		System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException{
		IndexPage ip = new IndexPage(driver);
		DesktopPage dp = ip.login(Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
		AdminPage ap = dp.goToAdminPage();
		AdminAddNewUserPage aanup = ap.addUser();
		aanup.addNewStudent("", "", "", "", "");
		assertEquals("You left some required fields empty",aanup.getMessage());
		aanup.doLogout();
	}
}
