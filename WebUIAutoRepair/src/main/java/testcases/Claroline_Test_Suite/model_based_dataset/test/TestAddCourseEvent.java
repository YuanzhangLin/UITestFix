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
import org.openqa.selenium.support.ui.Select;

public class TestAddCourseEvent {
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
    public void testAddCourseTestEvent() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.xpath("//a[contains(text(),'Computer Science')]")).click();
        driver.findElement(By.id("CLCAL")).click();
        driver.findElement(By.linkText("Add an event")).click();
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("Exam 001");
        driver.findElement(By.id("fday")).click();
        new Select(driver.findElement(By.id("fday"))).selectByVisibleText("26");
        driver.findElement(By.id("fmonth")).click();
        new Select(driver.findElement(By.id("fmonth"))).selectByVisibleText("December");
        driver.findElement(By.id("fyear")).click();
        new Select(driver.findElement(By.id("fyear"))).selectByVisibleText("2023");
        driver.findElement(By.name("submitEvent")).click();
        //try {
            Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"courseRightContent\"]/div[2]/div")).getText(),
                    "Event added to the agenda.");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }
}
