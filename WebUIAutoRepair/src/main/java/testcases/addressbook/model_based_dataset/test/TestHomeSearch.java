package testcases.addressbook.model_based_dataset.test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import testcases.Constants;

public class TestHomeSearch {
	WebDriver driver;
	Random random = new Random();

	@BeforeMethod
	public void before() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(Constants.getAddressBookUrl());
	}

	@Test(priority = 0)
	public void testhomeSearch() {
		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("Zeeshan");

	}

	@AfterMethod
	public void end () {
		driver.quit();
	}

}
