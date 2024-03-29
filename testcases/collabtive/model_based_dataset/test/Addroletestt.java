package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Addroletestt {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);

    }

    @Test(priority = 24)
    public void addroletestt() throws InterruptedException, IOException {
//        driver.get(Constants.getCollabtiveURL()+"admin.php?action=projects&mode=added");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/a")));
        actions.perform();
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[2]/a")).click();
        driver.findElement(By.id("addrolelink")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".main:nth-child(2) #name")).click();
        driver.findElement(By.cssSelector(".main:nth-child(2) #name")).sendKeys("farhan");
        driver.findElement(By.name("permissions_projects[add]")).click();
        driver.findElement(By.name("permissions_tasks[add]")).click();
        driver.findElement(By.cssSelector(".main:nth-child(2) button:nth-child(2)")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_green")).getText(), ("Role was added"));
        System.out.println("Asserted24");
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
