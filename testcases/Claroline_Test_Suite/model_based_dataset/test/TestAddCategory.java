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


public class TestAddCategory {
    WebDriver driver;
    static int in = 1;
    private StringBuffer verificationErrors = new StringBuffer();
    private boolean acceptNextAlert = true;

    @BeforeMethod
    public void before() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containCategory("Software Eng","SE112")){
            SQLProcess.deleteCategory("Software Eng","SE112");
        }
        SQLProcess.close();
    }

    @Test
    public void testAddCategory() throws Exception {
        char[] s = Character.toChars(in);
        Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Manage course categories")).click();
        driver.findElement(By.linkText("Create a category")).click();
        driver.findElement(By.id("category_name")).clear();
        driver.findElement(By.id("category_name")).sendKeys("Software Eng");
        driver.findElement(By.id("category_code")).clear();
        driver.findElement(By.id("category_code")).sendKeys("SE112" + s.toString());
        in++;
        driver.findElement(By.id("hidden")).click();
        driver.findElement(By.id("visible")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Category created[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
    }

    @AfterMethod
    public void end() throws SQLException, ClassNotFoundException, IOException {
        driver.quit();
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containCategory("Software Eng","SE112")){
            SQLProcess.deleteCategory("Software Eng","SE112");
        }
        SQLProcess.close();
    }
}
