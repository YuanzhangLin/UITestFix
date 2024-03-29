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

public class RunReportTest {
    private static WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(MRBSConstants.BASE_URL);
        Area.addArea("Area New", "888");
        Room.addRoom("Room New", "10", "888");
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
//        Entry.addEntry(driver, "Demo Match", "Match Description, Description, Description ");

    }

    @Test(priority = 0)
    public static void addAreaTest() throws Exception {

        driver.findElement(By.xpath("//a[@href='report.php']")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("namematch")).clear();
        driver.findElement(By.name("namematch")).sendKeys("Demo Match");
        driver.findElement(By.name("descrmatch")).clear();
        driver.findElement(By.name("descrmatch")).sendKeys("Match Description, Description, Description ");
        driver.findElement(By.xpath("//input[@value='Run Report']")).click();
        Thread.sleep(2000);
        //try {
        Assert.assertEquals(driver.findElement(By.xpath("//b[text()='1 entry found']")).getText(), "1 entry found");
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
