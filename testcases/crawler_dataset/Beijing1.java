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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Beijing1 {
    private static WebDriver driver;

@BeforeTest    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getBeijing());
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public static void test() throws Exception {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/ul/li[11]/a")).click();//市集
        String url = (String) ((JavascriptExecutor)driver).executeScript("var url;url =window.location.href;return url");
        System.out.println(url);
        Set<String> windowHandles3 = driver.getWindowHandles();
        WebDriver window=null;
        for (String string : windowHandles3) {//切换到新打开的标签页
            //count++;
            window = driver.switchTo().window(string);
            //System.out.println(window.getCurrentUrl());
            //System.out.println(window.getTitle());
            url = (String) ((JavascriptExecutor) window).executeScript("var url;url =window.location.href;return url");
            //System.out.println(url);
        }
        try {
            AssertJUnit.assertTrue(url.toLowerCase(Locale.ROOT).contains("market"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
