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



public class LoginNegativeTest {
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
		lp = lp.loginWrong("", "");
		assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.", lp.getErrorMsg());
	}
}