package testcases.Claroline_Test_Suite.study.test;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.study.po.DesktopPage;
import testcases.Claroline_Test_Suite.study.po.IndexPage;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class Claroline_LoginUserTest {
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
		DesktopPage dp = ip.login("user001", "password001");
		assertEquals("Firstname001 Name001", dp.getUserProfile());
		dp.doLogout();
	}
}
