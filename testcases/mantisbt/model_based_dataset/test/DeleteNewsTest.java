package testcases.mantisbt.model_based_dataset.test;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.mantisbt.model_based_dataset.po.News;

import java.util.concurrent.TimeUnit;

public class DeleteNewsTest {
    private static WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getMantisUrl());
        // Login User Administrator
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(Constants.Mantisbt_ADMIN_USER_NAME);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Constants.Mantisbt_ADMIN_PASSWORD);
        WebElement webElement = driver.findElement(By.xpath("//input[@value='Login']"));
        webElement.click();
        Thread.sleep(2000);
        News.addNews("HeadLine","add");
    }

    @Test(priority = 0)
    public static void newsTest() throws Exception {
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[10]")).click();
        WebElement dropdown1 = driver.findElement(By.name("news_id"));
        Select dropdownEle1 = new Select(dropdown1);
        dropdownEle1.selectByVisibleText("HeadLine");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='delete']")).click();
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Delete News Item']")).click();
        Thread.sleep(5000);
        AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Add News");     }

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
