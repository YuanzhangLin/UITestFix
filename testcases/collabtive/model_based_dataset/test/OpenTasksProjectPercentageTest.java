package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OpenTasksProjectPercentageTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);

    }

    @Test(priority = 36)
    public void OpenTasksProjectPercentageTest() throws InterruptedException {
        // *[@id="closetoggle"]/a
//        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[3]/a")).click();
//        driver.findElement(By.xpath("//*[@id=\"donebutn_1\"]")).click();
//        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"desktopprojects\"]/tbody[1]/tr[1]/td[2]/div/a")).click();
        Thread.sleep(2000);
        driver.navigate().refresh();
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[1]/a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".status")).getText(), ("0%"));
        // assert
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
