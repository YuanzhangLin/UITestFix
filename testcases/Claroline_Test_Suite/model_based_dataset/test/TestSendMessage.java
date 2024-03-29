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

public class TestSendMessage {
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
    public void testSendMessage() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Send a message to all users")).click();
        driver.findElement(By.id("message_subject")).clear();
        driver.findElement(By.id("message_subject")).sendKeys("Hello");
        driver.findElement(By.id("message_fontselect_open")).click();
        driver.findElement(By.cssSelector("#mce_18_aria > span.mceText")).click();
//        driver.findElement(By.cssSelector("span.mceIcon.mce_bold")).click();
//        driver.findElement(By.cssSelector("span.mceIcon.mce_italic")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(
                    driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Administrator[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        driver.findElement(By.name("send")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void end() {
        driver.quit();
    }
}
