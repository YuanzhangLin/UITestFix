package testcases.addressbook.model_based_dataset.test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import testcases.Constants;

public class TestVerifyWrongEntry {
	WebDriver driver;
	Random random = new Random();
	int rand_number = random.nextInt(1520000);
	static int expected_row_count = 0;
	private static String downloadPath = "C:\\Users\\A653\\Downloads";

	@BeforeMethod
	public void before() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(Constants.getAddressBookUrl());
		expected_row_count = driver
				.findElements(By.cssSelector("table#maintable.sortcompletecallback-applyZebra>tbody>tr")).size();
		System.out.println(expected_row_count);
	}

	@Test(priority = 0)
	void testVerifyWrongEntry() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='nav']/ul/li[1]/a")).click();
		Thread.sleep(100);
		driver.findElement(By.name("searchstring")).sendKeys("sulemana");
		driver.findElement(By.name("searchstring")).click();
		Assert.assertEquals(true, driver.getPageSource().contains("Number of results:"));
		String noRecord = driver.findElement(By.xpath("/html/body/div/div[4]/label/strong/span")).getText();
		Assert.assertEquals("0", noRecord);

	}
	@AfterMethod
	public void end () {
		driver.quit();
	}


}
