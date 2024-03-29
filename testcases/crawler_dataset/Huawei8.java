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

public class Huawei8 {
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
        driver.findElement(By.xpath("/html/body/footer/div[1]/div[2]/div[2]/ul/li[4]/a")).click();//huawei facts
        String url = (String) ((JavascriptExecutor)driver).executeScript("var url;url =window.location.href;return url");

        try {
            AssertJUnit.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/h1")).getText().
                    toLowerCase(Locale.ROOT).contains("fact")||
                    url.toLowerCase(Locale.ROOT).contains("fact"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
