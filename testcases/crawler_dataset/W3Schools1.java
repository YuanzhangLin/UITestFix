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

import java.util.concurrent.TimeUnit;


public class W3Schools1 {
    private static WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getW3SchoolsUrl());
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public static void test() throws Exception {
        driver.findElement(By.xpath("/html/body/div[4]/div/a[3]")).click();//example
        driver.findElement(By.xpath("/html/body/nav[3]/div/div[3]/a[3]")).click();//razor
        try {
            AssertJUnit.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/h1"))
                    .getText().contains("ASP.NET Web Pages - Examples in C# and VB"));

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

