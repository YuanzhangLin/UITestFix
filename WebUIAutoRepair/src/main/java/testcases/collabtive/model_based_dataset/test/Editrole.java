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

public class Editrole {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);

    }

    @Test(priority = 25)
    public void editrole() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/a")));
        actions.perform();
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"acc-roles\"]/table/tbody[1]/tr[1]/td[2]/div/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"acc-roles\"]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[3]/div[4]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"acc-roles\"]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[3]/div[10]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"acc-roles\"]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[3]/div[17]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"acc-roles\"]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[5]/button[1]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_yellow")).getText(), ("Role was edited"));
        System.out.println("Asserted25");
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
