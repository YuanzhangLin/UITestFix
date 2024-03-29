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

public class TestEditTextZone {
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
    public void testEditTextZone() throws Exception {
        Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Edit text zones")).click();
        driver.findElement(By.cssSelector("img[alt=\"Preview\"]")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*textzone_top\\.inc\\.html[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        driver.findElement(By.xpath("(//img[@alt='Preview'])[5]")).click();
        driver.findElement(By.cssSelector("img[alt=\"Edit\"]")).click();
        driver.findElement(By.cssSelector("input.claroButton")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"claroBody\"]/div[2]/div/div/div")).getText()
                    .equals("The changes have been carried out correctly\n" +
                            "textzone_top.inc.html"));
        //} catch (Error e) {
        //    verificationErrors.append(e.toString());
        //}
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }
}
