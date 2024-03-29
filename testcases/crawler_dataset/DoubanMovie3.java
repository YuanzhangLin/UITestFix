package testcases.crawler_dataset;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testcases.Constants;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DoubanMovie3 {
    private static WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getDoubanMovie());
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public static void test() throws Exception {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/a[2]")).click();//注册
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/span[2]/a[7]")).click();//移动应用
        System.out.println(driver.getCurrentUrl());
        try {
            AssertJUnit.assertTrue(driver.getCurrentUrl().toLowerCase(Locale.ROOT).contains("mobile")
                    ||driver.getCurrentUrl().toLowerCase(Locale.ROOT).contains("app"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
