package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;
import testcases.collabtive.model_based_dataset.po.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class AddMultipleProjectsTest {


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

    @Test(priority = 1)
    void addProject1() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"add_butn_myprojects\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("huzaim");
        driver.findElement(By.xpath("//*[@id=\"desc_ifr\"]")).sendKeys("final ride");
        driver.findElement(By.xpath("//*[@id=\"end\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"add_project\"]/table/tbody/tr[7]/td[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"add_project\"]/table/tbody/tr[8]/td/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"budget\"]")).sendKeys("1000");
        driver.findElement(By.xpath("//*[@id=\"3\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"form_addmyproject\"]/div/form/fieldset/div[9]/button[1]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"add_butn_myprojects\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("other huzaim");
        driver.findElement(By.xpath("//*[@id=\"desc_ifr\"]")).sendKeys("one last ride");
        driver.findElement(By.xpath("//*[@id=\"end\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"add_project\"]/table/tbody/tr[7]/td[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"add_project\"]/table/tbody/tr[8]/td/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"budget\"]")).sendKeys("2000");
        driver.findElement(By.xpath("//*[@id=\"form_addmyproject\"]/div/form/fieldset/div[7]/div/div[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"form_addmyproject\"]/div/form/fieldset/div[9]/button[1]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"add_butn_myprojects\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("another huzaim");
        driver.findElement(By.xpath("//*[@id=\"desc_ifr\"]")).sendKeys("someone stop th ride");
        driver.findElement(By.xpath("//*[@id=\"end\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"add_project\"]/table/tbody/tr[7]/td[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"add_project\"]/table/tbody/tr[8]/td/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"budget\"]")).sendKeys("3000");
        driver.findElement(By.xpath("//*[@id=\"form_addmyproject\"]/div/form/fieldset/div[7]/div/div[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"form_addmyproject\"]/div/form/fieldset/div[9]/button[1]")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_green")).getText(), ("Project was added"));
    }
    @AfterMethod
    public void end(){
        driver.quit();
    }
}
