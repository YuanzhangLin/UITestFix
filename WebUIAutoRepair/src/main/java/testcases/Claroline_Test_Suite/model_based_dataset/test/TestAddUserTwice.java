package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;
import testcases.Constants;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;

public class TestAddUserTwice {
    private WebDriver driver;
    private boolean acceptNextAlert = true;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containUser("user001", "user001")) {
            SQLProcess.deleteUser("user001", "user001");
        }
        SQLProcess.close();
    }

    @Test
    public void testAddUserTwice() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("User list")).click();
        driver.findElement(By.id("claroBody")).click();
        driver.findElement(By.linkText("Create user")).click();
        driver.findElement(By.id("lastname")).click();
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("user001");
        driver.findElement(By.id("firstname")).click();
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys("user001");
        driver.findElement(By.id("officialCode")).click();
        driver.findElement(By.id("officialCode")).clear();
        driver.findElement(By.id("officialCode")).sendKeys("001");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("user001");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("user001");
        driver.findElement(By.id("password_conf")).click();
        driver.findElement(By.id("password_conf")).clear();
        driver.findElement(By.id("password_conf")).sendKeys("user001");
        driver.findElement(By.id("applyChange")).click();
        driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).click();
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("User list")).click();
        driver.findElement(By.id("claroBody")).click();
        driver.findElement(By.linkText("Create user")).click();
        driver.findElement(By.id("lastname")).click();
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("user001");
        driver.findElement(By.id("firstname")).click();
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys("user001");
        driver.findElement(By.id("officialCode")).click();
        driver.findElement(By.id("officialCode")).clear();
        driver.findElement(By.id("officialCode")).sendKeys("002");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("user001");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("user001");
        driver.findElement(By.id("password_conf")).click();
        driver.findElement(By.id("password_conf")).clear();
        driver.findElement(By.id("password_conf")).sendKeys("user001");
        driver.findElement(By.id("applyChange")).click();
        driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).click();
        assertEquals(driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).getText(), "This username is already taken");
    }

    @AfterMethod

    public void tearDown() throws Exception {
        driver.quit();
      SQL_Process SQLProcess = new SQL_Process();
      if (SQLProcess.containUser("user001", "user001")) {
        SQLProcess.deleteUser("user001", "user001");
      }
      SQLProcess.close();
    }

}
