package testcases.Claroline_Test_Suite.model_based_dataset.test;
import config.DriverConfig;
import org.testng.annotations.*;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;

public class TestLoginLogout {
	WebDriver driver;
	static int in = 1;
	private StringBuffer verificationErrors = new StringBuffer();
	private boolean acceptNextAlert = true;

	@BeforeMethod
	public void before() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());

	}

	@Test
	public void testLoginLogout() throws Exception {
		Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
		driver.findElement(By.linkText("Logout")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"loginBox\"]/h3")).getText().equals("Authentication"));
		//try {
//			Assert.assertTrue(
//					driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Authentication[\\s\\S]*$"));
		//} catch (Error e) {
		//	verificationErrors.append(e.toString());
		//}
	}

	@AfterMethod

	public void end(){
		driver.quit();
	}
}
