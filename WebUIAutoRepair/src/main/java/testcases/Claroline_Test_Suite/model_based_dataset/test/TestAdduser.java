package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.testng.annotations.*;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;
import testcases.Constants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TestAdduser {
    WebDriver driver;

    @BeforeMethod
    public void before() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containUser("Naman","Agr")){
            SQLProcess.deleteUser("Naman","Agr");
        }
        SQLProcess.close();
    }

    @Test
    public void testAddUser() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Create user")).click();
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("Naman");
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys("Agr");
        driver.findElement(By.id("officialCode")).clear();
        driver.findElement(By.id("officialCode")).sendKeys("a7");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("nam");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("naman");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("password_conf")).clear();
        driver.findElement(By.id("password_conf")).sendKeys("123456");
        driver.findElement(By.id("student")).click();
        driver.findElement(By.id("student")).click();
        driver.findElement(By.id("student")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(
                    driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*\\(student\\)[\\s\\S]*$"));
//        } catch (Error e) {
//            System.out.println(e);
//        }
        driver.findElement(By.id("applyChange")).click();
        Assert.assertEquals("Create a new user - Claroline", driver.getTitle());
    }

    @AfterMethod
    public void end() throws SQLException, ClassNotFoundException, IOException {
        driver.quit();
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containUser("Naman","Agr")){
            SQLProcess.deleteUser("Naman","Agr");
        }
        SQLProcess.close();
    }
}
