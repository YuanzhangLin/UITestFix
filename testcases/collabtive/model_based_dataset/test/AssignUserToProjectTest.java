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

public class AssignUserToProjectTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Project.cleanProject();
        Project.addProject("Project");
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);
    }

    @Test(priority = 16)
    void AssignUserToProjectTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"desktopprojects\"]/tbody/tr[1]/td[5]/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"contentwrapper\"]/div[1]/ul/li[6]/a")).click();
        //*[@id=\"contentwrapper\"]/div[1]/ul/li[3]/a
        driver.findElement(By.xpath("//*[@id=\"add_butn_member\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"addtheuser\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"addtheuser\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form_member\"]/div/form/fieldset/div[2]/button[1]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_yellow")).getText(),
                ("User was assigned"));
        Thread.sleep(2000);
        System.out.println("Asserted16");
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
