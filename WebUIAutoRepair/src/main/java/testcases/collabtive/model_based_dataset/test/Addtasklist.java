package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Addtasklist {
    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants
                .Collabtive_ADMIN_PASSWORD);

    }


    @Test(priority = 0)
    void Addmilestone() throws InterruptedException, IOException {
        driver.get(Constants.getCollabtiveURL() + "admin.php?action=projects&mode=added");

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
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[1]/a")).click();
        Assert.assertEquals(driver.findElement(By.linkText("My tasks")).getText(), ("My tasks")); }

    @AfterMethod
    public void end() {
        driver.quit();
    }

}

