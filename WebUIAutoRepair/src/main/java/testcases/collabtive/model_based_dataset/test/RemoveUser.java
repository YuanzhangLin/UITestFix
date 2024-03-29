package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;
import testcases.collabtive.model_based_dataset.po.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class RemoveUser {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver, Constants.Collabtive_ADMIN_USER_NAME, Constants.Collabtive_ADMIN_PASSWORD);
        User.addUser("AA");
    }

    @Test(priority = 28)
    public void removeuser() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/a")));
        actions.perform();
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[2]/a")).click();
        int original =
                driver.findElement(By.xpath("//*[@id=\"sm_member\"]/div[2]/div/div"))
                        .findElements(By.tagName("li")).size();
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"sm_member\"]/div[2]/div/div/ul/li[1]/div/table/tbody/tr[1]/td[2]/a/img"));
        builder.clickAndHold(element).build().perform();
        Thread.sleep(1000);
        driver.findElements(By.cssSelector(".del")).get(0).click();
        driver.switchTo().alert().accept();

//        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[1]/form/fieldset/div[4]/button[1]"));
        int end =
                driver.findElement(By.xpath("//*[@id=\"sm_member\"]/div[2]/div/div"))
                        .findElements(By.tagName("li")).size();
        Assert.assertEquals(original, end + 1);
//        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_red")).getText(), ("User was deleted"));

    }

    @AfterMethod
    public void end() {
        driver.quit();
    }
}
