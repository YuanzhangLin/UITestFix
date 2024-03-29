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

public class TestViewProfile {
    WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void before() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());

    }

    @Test
    public void testViewProfile() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Right profile list")).click();
        // Warning: verifyTextPresent may require manual changes
       // try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Course member \\(the user is actually enrolled in the course\\)[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        driver.findElement(By.xpath("(//img[@alt='Edit'])[6]")).click();
        Assert.assertEquals("Claroline", driver.getTitle());
        driver.findElement(By.cssSelector("a > span")).click();
        driver.findElement(By.cssSelector("a > span")).click();
        driver.findElement(By.cssSelector("a > span")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Access allowed[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        driver.findElement(By.linkText("View all right profile")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"claroBody\"]/div[1]/span")).getText().equals("All profiles"));
//            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches(
//                    "^[\\s\\S]*Agenda 	Access allowed Access allowed 	Access allowed Access allowed 	Access allowed Access allowed 	Edition allowed Edition allowed[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
    }

    @AfterMethod(alwaysRun = true)
    public void end() {
        driver.quit();
    }
}
