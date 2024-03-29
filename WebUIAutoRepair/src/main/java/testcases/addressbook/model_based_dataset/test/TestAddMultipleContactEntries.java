package testcases.addressbook.model_based_dataset.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testcases.Constants;
import testcases.addressbook.model_based_dataset.po.Contact;

import static org.testng.AssertJUnit.assertTrue;

public class TestAddMultipleContactEntries {
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
    void testAddMultipleContactEnteries() throws InterruptedException {
        Contact cnt = new Contact(null, null, null, null, null, null, null, null, null);
        ArrayList<Contact> list = cnt.readContactData();
        for (int i = 0; i < list.size(); i++) {
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id='nav']/ul/li[2]/a")).click();
            driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys(list.get(i).getFirst_name());
            driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys(list.get(i).getLast_name());
            driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys(list.get(i).getCompany());
            driver.findElement(By.xpath("//TEXTAREA[@name='address']")).sendKeys(list.get(i).getAddress());
            driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys(list.get(i).getPhone());
            driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).clear();
            driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).sendKeys(list.get(i).getEmail());
            new Select(driver.findElement(By.xpath("//SELECT[@name='bday']"))).selectByValue(list.get(i).getDay());
            new Select(driver.findElement(By.xpath("//SELECT[@name='bmonth']"))).selectByValue(list.get(i).getMonth());
            driver.findElement(By.xpath("(//INPUT[@type='text'])[11]")).sendKeys(list.get(i).getYear());
            driver.findElement(By.xpath("//INPUT[@type='submit']")).click();
            String message = driver.findElement(By.className("msgbox")).getText();
            assertTrue(message.contains("Information entered into address book."));
            Thread.sleep(100);
        }
    }


	@AfterMethod
	public void end () {
		driver.quit();
	}


}
