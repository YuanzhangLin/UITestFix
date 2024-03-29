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
import testcases.collabtive.model_based_dataset.po.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class AddAndRemoveMultipleTasksTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        Task.addTask();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);

    }

    @Test(priority = 34)
    public void AddAndRemoveMultipleTasksTest() throws InterruptedException, IOException {
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[4]/div[3]/div/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).sendKeys("tasksss");
        driver.findElement(By.xpath("//*[@id=\"tasklist\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"tasklist\"]/option[2]")).click();
        {
//            WebElement dropdown = driver.findElement(By.name("assigned[]"));
//            dropdown.findElements(By.tagName("option")).get(2).click();
        }
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[4]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[1]/a")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[4]/div[3]/div/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).sendKeys("sss");
//        driver.findElement(By.xpath("//*[@id=\"tasklist\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"tasklist\"]/option[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"tasklist\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"tasklist\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[4]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[1]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[4]/div[3]/div/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).sendKeys("kkkk");
        driver.findElement(By.xpath("//*[@id=\"tasklist\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"tasklist\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[4]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[1]/a")).click();

        Assert.assertEquals(driver.findElement(By.linkText("My tasks")).getText(), ("My tasks"));

    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
