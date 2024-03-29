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

public class TestAddNewContact {
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
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a")).click();
	}

	@Test(priority = 0)
	public void testaddNewContact() throws InterruptedException {

		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Muhammad");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("Suleman");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("Quest Lab");
		driver.findElement(By.xpath("//TEXTAREA[@name='address']")).sendKeys("Rothas Road G9/4 lamabad");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("03165282707");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[5]")).sendKeys("03341006096");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[6]")).sendKeys("-----");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[7]")).sendKeys("----");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).sendKeys("msuleman526@gmail.com");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[9]")).sendKeys("msuleman526@gmail.com");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[10]")).sendKeys("-------");
		new Select(driver.findElement(By.xpath("//SELECT[@name='bday']"))).selectByValue("13");
		new Select(driver.findElement(By.xpath("//SELECT[@name='bmonth']"))).selectByValue("November");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[11]")).sendKeys("1995");
		driver.findElement(By.xpath("//TEXTAREA[@name='address2']")).sendKeys("Rothas Road G9/4 lamabad");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[12]")).sendKeys("");
		driver.findElement(By.xpath("//TEXTAREA[@name='notes']")).sendKeys("");
		driver.findElement(By.xpath("//INPUT[@type='submit']")).click();
		String message = driver.findElement(By.className("msgbox")).getText();
		Assert.assertTrue(message.contains("Information entered into address book."));
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
		int actual_row_count = driver
				.findElements(By.cssSelector("table#maintable.sortcompletecallback-applyZebra>tbody>tr")).size();
		System.out.println("now after add=" + actual_row_count);
		Assert.assertTrue(actual_row_count > expected_row_count); // In case of addition of new entry, table size should
																	// be upgraded with 1
	}
	@AfterMethod
	public void end () {
		driver.quit();
	}

}
