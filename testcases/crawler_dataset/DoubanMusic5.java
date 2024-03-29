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

public class DoubanMusic5 {
    private static WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getDoubanMusic());
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public static void test() throws Exception {
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[1]/div/h2/span/a")).click();//本周流行音乐人旁边的“更多”
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[3]/div[8]/div[2]/a[1]")).click();//申请成为音乐人
        Thread.sleep(5000);//TODO wayback machine可能还来不及跳转
        System.out.println(driver.getCurrentUrl());
        try {
            AssertJUnit.assertTrue(driver.getCurrentUrl().
                    toLowerCase(Locale.ROOT).contains("login")  );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
