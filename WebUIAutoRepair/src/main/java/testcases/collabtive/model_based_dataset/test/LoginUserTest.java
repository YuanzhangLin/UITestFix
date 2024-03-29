
package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginUserTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
    }

    @Test
    void performLogin() {
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Constants.Collabtive_ADMIN_USER_NAME);
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(Constants.Collabtive_ADMIN_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"loginform\"]/fieldset/div[4]/button")).click();
        Assert.assertEquals(driver.getTitle(), ("Desktop @ Collabtive"));
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
