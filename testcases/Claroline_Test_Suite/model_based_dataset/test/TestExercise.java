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

public class TestExercise {
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
    public void testExercise() throws Exception {

        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);

        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Configuration")).click();
        driver.findElement(By.linkText("Exercises")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches(
                    "^[\\s\\S]*Properties for Exercises, \\(CLQWZ\\) are now effective on server\\.[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Automatically insert an event in the calendar at the end date[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
        driver.findElement(By.id("label_exercisesPerPage")).clear();
        driver.findElement(By.id("label_exercisesPerPage")).sendKeys("20");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Powered by Claroline [\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
    }

	@AfterMethod
    public void end() {
        driver.quit();
    }
}
