package testcases.addressbook.model_based_dataset.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import testcases.Constants;
import testcases.addressbook.model_based_dataset.po.Group;

public class TestAddMultipleGroups {
	WebDriver driver;
	Random random = new Random();
	int rand_number = random.nextInt(1520000);
	static int expected_row_count = 0;

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
	void testAddMultipleMultipleGroups() throws InterruptedException {
		Group grp = new Group(null, null, null);
		ArrayList<Group> list = grp.readGroupData();
		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			driver.findElement(By.xpath("//*[@id='nav']/ul/li[3]/a")).click();
			driver.findElement(By.xpath("//*[@id=\"content\"]/form[1]/input")).click();
			driver.findElement(By.xpath("//INPUT[@type='text']/self::INPUT")).sendKeys(list.get(i).getGroup_name());
			driver.findElement(By.xpath("//TEXTAREA[@name='group_header']/self::TEXTAREA"))
					.sendKeys(list.get(i).getGroup_header());
			driver.findElement(By.xpath("//TEXTAREA[@name='group_footer']/self::TEXTAREA"))
					.sendKeys(list.get(i).getGroup_footer());
			driver.findElement(By.xpath("//INPUT[@type='submit']/self::INPUT")).click();
			String message = driver.findElement(By.className("msgbox")).getText();
			Assert.assertTrue(message.contains("A new group has been entered into the address book."));

		}
	}
	@AfterMethod
	public void end () {
		driver.quit();
	}

}
