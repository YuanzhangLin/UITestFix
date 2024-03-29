package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.testng.annotations.*;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAddCourseExercise {
    WebDriver driver;
    static int in = 1;
    private StringBuffer verificationErrors = new StringBuffer();
    private boolean acceptNextAlert = true;

    @BeforeMethod
    public void before() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
    }

    @Test
    public void testAddCourseExcercise() throws Exception {
        Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"dekstopLeftSidebar\"]/div/div/div[2]/dl/dt[1]/span/a")).click();
        driver.findElement(By.id("CLQWZ")).click();
        driver.findElement(By.linkText("New exercise")).click();
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("Excercise 001");
        driver.findElement(By.xpath("//input[@id='']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"courseRightContent\"]/div[2]/div")).getText(),
                "Exercise added");
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }
}
