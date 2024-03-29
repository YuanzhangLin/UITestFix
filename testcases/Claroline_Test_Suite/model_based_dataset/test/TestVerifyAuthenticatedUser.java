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

public class TestVerifyAuthenticatedUser {
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
    public void testVerifyAuthenticatedUser() throws Exception {
		Login.login(driver,Constants.Claroline_ADMIN_USER_NAME
                ,Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Show/Hide")).click();
        Thread.sleep(1500);
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*textzone_top\\.authenticated\\.inc\\.html[\\s\\S]*$"));
        //} catch (Error e) {
        //    verificationErrors.append(e.toString());
        //}
        driver.findElement(By.linkText("View all my messages")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"claroBody\"]/div[1]/table/tbody/tr/td[1]/h1")).getText(), "MY MESSAGES");
        //} catch (Error e) {
        //    verificationErrors.append(e.toString());
        //}
    }

    @AfterMethod(alwaysRun = true)
    public void end() {
        driver.quit();
    }

}
