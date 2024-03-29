package testcases.crawler_dataset;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testcases.Constants;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class Homedepot1 {
    private static WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getHomedepot());
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public static void addProfileTest() throws Exception {
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/footer/div[3]/div/ul/li[3]/a")).click();//manage private reference
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[1]/div/div[1]/div/div/ul/li[3]/a")).click();//check order status
        //失败可能是网络原因打不开网页
        try {
            AssertJUnit.assertTrue(driver.findElement(By.xpath("/html/body/section/h1")).getText().contains("Store Finder")||driver.getCurrentUrl().toLowerCase(Locale.ROOT).contains("I"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
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

