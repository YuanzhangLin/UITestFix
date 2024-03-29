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

public class TestAddAdmin {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
    }

    @Test
    public void testAddAdmin() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        SQL_Process SQLProcess = new SQL_Process();
        if (!SQLProcess.containUser("Admin001","Admin001")){
            SQLProcess.deleteUser("Admin001","Admin001");
        }
        SQLProcess.close();
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Create user")).click();
        driver.findElement(By.id("lastname")).click();
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("Admin001");
        driver.findElement(By.id("firstname")).click();
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys("Admin001");
        driver.findElement(By.id("officialCode")).click();
        driver.findElement(By.id("officialCode")).clear();
        driver.findElement(By.id("officialCode")).sendKeys("0001");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("123456");
//        driver.findElement(By.xpath("//form[@id='userSettings']/fieldset[2]/dl")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin001");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("admin001");
        driver.findElement(By.id("password_conf")).click();
        driver.findElement(By.id("password_conf")).clear();
        driver.findElement(By.id("password_conf")).sendKeys("admin001");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("admin001@claroline");
        driver.findElement(By.id("platformAdmin")).click();
        driver.findElement(By.id("applyChange")).click();
        driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).click();
        assertEquals(driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).getText(), "The new user has been sucessfully created");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containUser("Admin001","Admin001")){
            SQLProcess.deleteUser("Admin001","Admin001");
        }
        SQLProcess.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
