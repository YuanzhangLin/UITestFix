package testcases.Claroline_Test_Suite.model_based_dataset.test;
import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestManageUser {
	WebDriver driver;
	static int in = 1;
	private StringBuffer verificationErrors = new StringBuffer();
	private boolean acceptNextAlert = true;

	@BeforeMethod
	public void before() throws IOException {
		System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(Constants.getClarolineURL());

	}

	@Test
	public void testManageUsers() throws Exception {
		Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);
		driver.findElement(By.linkText("Platform administration")).click();
		driver.findElement(By.linkText("Manage user desktop")).click();
		driver.findElement(By.cssSelector("img[alt=\"visible\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"invisible\"]")).click();
		// Warning: verifyTextPresent may require manual changes
		//try {
			Assert.assertTrue(
					driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*My course list[\\s\\S]*$"));
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}
		// Warning: verifyTextPresent may require manual changes
		//try {
			Assert.assertTrue(
					driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]* Claroline[\\s\\S]*$"));
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}
		driver.findElement(By.linkText("Logout")).click();
	}

	@AfterMethod

	public void end(){
		driver.quit();
	}
}
