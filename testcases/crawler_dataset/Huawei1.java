package testcases.crawler_dataset;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testcases.Constants;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Huawei1 {
    private static WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getHuawei());
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public static void test() throws Exception {
        driver.findElement(By.xpath("/html/body/footer/div[1]/div[2]/div[1]/div/ul[1]/li[2]/a")).click();//annual report
        String url = (String) ((JavascriptExecutor)driver).executeScript("var url;url =window.location.href;return url");

        try {
            AssertJUnit.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/h1")).getText().
                    toLowerCase(Locale.ROOT).contains("annual report")||url.toLowerCase(Locale.ROOT).contains("annual")&&
                    url.toLowerCase(Locale.ROOT).contains("report"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}