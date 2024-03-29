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

public class SearchMultipleProjectsTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);
        Project.cleanProject();
        Project.addProject("other huzaim");
        Project.addProject("another huzaim");
    }

    @Test(priority = 38)
    public void SearchMultipleProjectsTest() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"query\"]")).sendKeys("other huzaim");
        driver.findElement(By.xpath("//*[@id=\"search\"]/fieldset/button")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("tr:nth-child(2) > td")).get(0).getText(), ("other huzaim"));

        // assert 2
        driver.findElement(By.xpath("//*[@id=\"query\"]")).sendKeys("another huzaim");
        driver.findElement(By.xpath("//*[@id=\"search\"]/fieldset/button")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("tr:nth-child(2) > td")).get(0).getText(), ("another hu..."));

        // assert 1

    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
