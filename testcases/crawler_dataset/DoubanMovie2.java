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

public class DoubanMovie2 {
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
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/p[2]/a")).click();//点击下载豆瓣应用
        System.out.println(driver.getTitle());
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
