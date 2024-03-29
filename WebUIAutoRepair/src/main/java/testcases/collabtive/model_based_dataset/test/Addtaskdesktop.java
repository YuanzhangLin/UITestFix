package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;
import testcases.collabtive.model_based_dataset.po.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Addtaskdesktop {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        Task.addTask();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);

    }

    @Test(priority = 31)
    void Addtaskdesktop() throws InterruptedException, IOException {
        driver.get(Constants.getCollabtiveURL()+"admin.php?action=projects&mode=added");
        driver.findElement(By.cssSelector(".tool_edit")).click();
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("addtasklists")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("taskname");
        driver.findElement(By.id("milestone")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button:nth-child(2)")).click();
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[4]/div/a[4]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).sendKeys("tasksss");

        {
            WebElement dropdown = driver.findElement(By.name("assigned[]"));
            dropdown.findElement(By.xpath("//option[. = 'admin']")).click();
        }
        driver.findElement(By.xpath("//*[@id=\"addtaskform1000\"]/fieldset/div[6]/button[1]")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[1]/a")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.linkText("My tasks")).getText(), ("My tasks"));

    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
