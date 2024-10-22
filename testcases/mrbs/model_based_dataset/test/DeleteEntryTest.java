package testcases.mrbs.model_based_dataset.test;
import config.DriverConfig;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import testcases.mrbs.model_based_dataset.sql.Area;
import testcases.mrbs.model_based_dataset.sql.Entry;
import testcases.mrbs.model_based_dataset.sql.MRBSConstants;
import testcases.mrbs.model_based_dataset.sql.Room;

public class DeleteEntryTest {
	private static WebDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(MRBSConstants.BASE_URL);
		Area.cleanArea();
		Area.addArea("Area New","888");
		Room.addRoom("Room New","10","888");
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
		//Area.addArea("Area New");

		Entry.addEntry(driver,"Demo Entry");
	}

	@Test(priority = 0)
	public static void deleteEntryTest() throws Exception {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//font[text()='Area New']")).click();

		driver.findElement(By.xpath("//a[text()='Go To Today']")).click();
		Thread.sleep(1000);
		// <WebElement> list = driver.findElements(By.xpath("//img[@src='new.gif']"));
		// list.get(0).click();
		Thread.sleep(1000);
		//try {
			driver.findElement(By.xpath("//a[@title='Demo Entry']")).click();
//		} catch (Exception e) {
//			driver.findElement(By.xpath("//a[text()='Demo Entry']")).click();
//		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/p[1]/a[3]")).click();
		driver.switchTo().alert().accept();
		//try {
			Assert.assertEquals(driver.findElement(By.xpath("//font[@color='red']")).getText(), "Area New");
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
