package testcases.mrbs.model_based_dataset.test;
import config.DriverConfig;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import testcases.mrbs.model_based_dataset.sql.Area;
import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.model_based_dataset.sql.Room;

public class AddMultipleRoomTest {
	private static WebDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(MRBSConstants.BASE_URL);

		// Login User Administrator
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value=' Log in ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("NewUserName")).clear();
		driver.findElement(By.name("NewUserName")).sendKeys(MRBSConstants.ADMIN_USER_NAME);
		driver.findElement(By.name("NewUserPassword")).clear();
		driver.findElement(By.name("NewUserPassword")).sendKeys(MRBSConstants.ADMIN_PASSWORD);
		driver.findElement(By.xpath("/html/body/form/input[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Admin']")).click();
		Thread.sleep(2000);

	}

	@Test(priority = 0)
	public static void addMultipleRoomTest() throws Exception {

		// Add Room One
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td[1]/ul/li[4]/a[1]")).click();
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[3]/td[2]/form/table/tbody/tr[1]/td[2]/input"))
				.clear();
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[3]/td[2]/form/table/tbody/tr[1]/td[2]/input"))
				.sendKeys("New Room");
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("Nice Room1");
		driver.findElement(By.name("capacity")).clear();
		driver.findElement(By.name("capacity")).sendKeys("10");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@value='Add Room']")).click();
		Thread.sleep(2000);

		// Add Room Two
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td[1]/ul/li[4]/a[1]")).click();
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[3]/td[2]/form/table/tbody/tr[1]/td[2]/input"))
				.clear();
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[3]/td[2]/form/table/tbody/tr[1]/td[2]/input"))
				.sendKeys("New Room");
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("Nice Room2");
		driver.findElement(By.name("capacity")).clear();
		driver.findElement(By.name("capacity")).sendKeys("10");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@value='Add Room']")).click();
		//try {
			Assert.assertEquals(driver.findElement(By.xpath("//li[text()='New Room(Nice Room1, 10) (']")).getText(),
					"New Room(Nice Room1, 10) (Edit) (Delete)");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

	public static void jsClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

}
