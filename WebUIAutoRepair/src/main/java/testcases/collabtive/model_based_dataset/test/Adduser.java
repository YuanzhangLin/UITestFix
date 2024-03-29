package testcases.collabtive.model_based_dataset.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;
import testcases.collabtive.model_based_dataset.po.User;

public class Adduser {
    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        User.deleteUser("Ali");
    }


    @Test
    void Adduser() throws InterruptedException {
        Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants
                .Collabtive_ADMIN_PASSWORD);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/a")));
        actions.perform();
        driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[3]/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"add_butn_member\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Ali");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Ali@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("Ali");
        driver.findElement(By.xpath("//*[@name=\"rate\"]")).sendKeys("ten");

//        driver.findElement(By.xpath("//*[@id=\"form_member\"]/div/form/fieldset/div[6]/div/div[1]/input")).click();
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"roleselect\"]")));
        dropdown.selectByVisibleText("Admin");
        driver.findElement(By.xpath("//*[@id=\"form_member\"]/div/form/fieldset/div[10]/div/button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_green")).getText(), ("User was added"));
    }
    @AfterMethod
	public void end(){
		driver.quit();
	}

}
