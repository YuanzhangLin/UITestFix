package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.testng.annotations.*;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMakeModuleVisible {
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
    public void testMakeModuleVisible() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Modules")).click();
        driver.findElement(By.cssSelector("img[alt=\"Properties\"]")).click();
        acceptNextAlert = false;
        driver.findElement(By.linkText("Visible")).click();
        Assert.assertTrue(closeAlertAndGetItsText()
                .contains("Are you sure you want to make this module visible in all courses"));
        driver.findElement(By.linkText("Local settings")).click();
        driver.findElement(By.linkText("About")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .contains("Course description"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        driver.findElement(By.xpath("//*[@id=\"navlist\"]/li[1]/a")).click();
        driver.findElement(By.linkText("Activated")).click();
        driver.findElement(By.linkText("Deactivated")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .contains("Module activation succeeded"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        driver.findElement(By.linkText("Automatic")).click();
        driver.findElement(By.linkText("Manual")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Module activation at course creation set to AUTOMATIC[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
    }

	@AfterMethod
    public void end() {
        driver.quit();
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
