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
import testcases.collabtive.model_based_dataset.po.Project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class RemoveProjectest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);
        Project.addProject("huzaim");
    }

    @Test(priority = 20)
    void RemoveProjectest() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"desktopprojects\"]/tbody/tr[1]/td[5]/a[2]")).click();
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.id("deleted")).getText(), ("Project was deleted"));
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
