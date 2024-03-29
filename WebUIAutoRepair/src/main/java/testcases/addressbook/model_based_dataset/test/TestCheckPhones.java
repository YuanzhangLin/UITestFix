package testcases.addressbook.model_based_dataset.test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import testcases.Constants;

public class TestCheckPhones {
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
	public void CheckPhonesTest() throws IOException {
		driver.findElement(By.xpath("//*[@id='nav']/ul/li[6]/a")).click();
		Assert.assertEquals(true, driver.getPageSource().contains("Muhammad Suleman"));
		Assert.assertEquals(true, driver.getPageSource().contains("03165282707"));
		Assert.assertEquals(true, driver.getPageSource().contains("03341006096"));
		driver.navigate().to(Constants.getAddressBookUrl());

	}

	@AfterMethod
	public void end () {
		driver.quit();
	}

}
