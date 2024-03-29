package testcases.addressbook.model_based_dataset.test;

import java.io.IOException;
import java.sql.SQLException;
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
import testcases.addressbook.model_based_dataset.po.Address;
import testcases.addressbook.model_based_dataset.po.Group;

public class TestHomeAddToAll {
	WebDriver driver;
	Random random = new Random();
	int rand_number = random.nextInt(1520000);
	static int expected_row_count = 0;
	private static String downloadPath = "C:\\Users\\A653\\Downloads";

	@BeforeMethod
	public void before() throws IOException, SQLException, ClassNotFoundException {
		System.setProperty("webdriver.chrome.driver",
				DriverConfig.DRIVER_PATH);
		Address.cleanAddress();
		Group.cleanGroup();
		Address.addAddress("MassCB","MassCB");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(Constants.getAddressBookUrl());
		expected_row_count = driver
				.findElements(By.cssSelector("table#maintable.sortcompletecallback-applyZebra>tbody>tr")).size();
		System.out.println(expected_row_count);
	}

	@Test(priority = 0)
	public void testhomeAddToAll() throws InterruptedException {
		driver.findElement(By.xpath("//INPUT[@id='MassCB']")).click();
		driver.findElement(By.xpath("//INPUT[@type='submit']")).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div")).getText().contains("Users added"));
	}
	@AfterMethod
	public void end () {
		driver.quit();
	}

}
