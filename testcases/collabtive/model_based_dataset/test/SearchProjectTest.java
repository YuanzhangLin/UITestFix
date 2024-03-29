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

public class SearchProjectTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);
        Project.addProject("oneproject");

    }

    @Test(priority = 37)
    public void SearchProjectTest() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"query\"]")).sendKeys("oneproject");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"search\"]/fieldset/button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td")).getText(), ("oneproject"));

        // assert 3

    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
