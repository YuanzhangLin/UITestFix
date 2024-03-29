package testcases.collabtive.model_based_dataset.test;

import config.DriverConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.collabtive.model_based_dataset.po.Login;
import testcases.collabtive.model_based_dataset.po.Project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class EditProjectTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        Project.addProject("huzaim");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getCollabtiveURL());
        Login.login(driver,Constants.Collabtive_ADMIN_USER_NAME,Constants.Collabtive_ADMIN_PASSWORD);
    }

    @Test
    void EditProjectTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"desktopprojects\"]/tbody/tr[1]/td[2]/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"edit_butn\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("changed name");
        driver.findElement(By.xpath("//*[@id=\"form_edit\"]/div[2]/form/fieldset/div[5]/button[1]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".info_in_yellow")).getText(), ("Project was edited"));
    }


    @AfterMethod
    public void end() throws SQLException, IOException, ClassNotFoundException {
        Project.deleteProject("changed name");
        driver.quit();
    }
}
