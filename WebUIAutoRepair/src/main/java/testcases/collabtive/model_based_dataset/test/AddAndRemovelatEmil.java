package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AddAndRemovelatEmil {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver, Constants.Collabtive_ADMIN_USER_NAME, Constants.Collabtive_ADMIN_PASSWORD);

    }

    @Test
    public void addandremovelatemil() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"desktopprojects\"]/tbody/tr[1]/td[2]/div/a")).click();
//        driver.findElement(By.cssSelector("#proj_1 .tool_edit")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(" //*[@id=\"contentwrapper\"]/div[1]/ul/li[2]/a")).click();

        driver.findElement(By.id("add_butn")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("latemilestone");

        driver.findElement(By.xpath("//*[@id=\"end\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"datepicker_miles\"]/table/tbody/tr[4]/td[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"datepicker_miles\"]/table/tbody/tr[8]/td/a")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"addstone\"]/div/form/fieldset/div[5]/button[1]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_green")).getText(), ("Milestone was added"));
        driver.findElement(By.xpath("//*[@id=\"accordion_miles_late\"]/tbody[1]/tr[1]/td[5]/a[1]")).click();
        //*[@id="miles_late_23"]/tr[1]/td[5]/a[2]
        driver.findElement(By.xpath("//*[@id=\"desc_ifr\"]")).sendKeys("changed milstone ride edited");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[2]/form/fieldset/div[5]/button")).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_yellow")).getText(), ("Milestone was edited"));

        driver.findElement(By.xpath("//*[@id=\"accordion_miles_late\"]/tbody/tr[1]/td[5]/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"desc_ifr\"]")).sendKeys("changed milstone ride edited");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div/div[2]/form/fieldset/div[5]/button")).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_yellow")).getText(), ("Milestone was edited"));

        driver.findElement(By.cssSelector(".tool_del")).click();
        Assert.assertEquals(driver.switchTo().alert().getText(),
                ("Really delete this item?\nDeleting cannot be undone."));
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_red")).getText(), ("Milestone was deleted"));
        // driver.navigate().refresh();

    }

    @AfterMethod
    public void end() {
        driver.quit();
    }
}
