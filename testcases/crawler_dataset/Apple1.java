package testcases.crawler_dataset;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testcases.Constants;

import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Apple1 {
    private static WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getApple());
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public static void test() throws Exception {
        driver.findElement(By.xpath("/html/body/footer/div/nav/div[1]/div/ul/li[7]/a")).click();//iTunes
        String url = (String) ((JavascriptExecutor) driver).executeScript("var url;url =window.location.href;return url");

        try {
            AssertJUnit.assertTrue(url.toLowerCase(Locale.ROOT).contains("itunes"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
